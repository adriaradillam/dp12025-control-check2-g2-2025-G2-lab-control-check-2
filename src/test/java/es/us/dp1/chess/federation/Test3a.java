package es.us.dp1.chess.federation;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.empty;

import com.fasterxml.jackson.core.JsonProcessingException;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Test3a {

@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;


	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
                    .webAppContextSetup(context)
                    .apply(SecurityMockMvcConfigurers.springSecurity())
                    .build();
	}    	

    @Test
    @Transactional
    @WithMockUser(username = "adminUser", authorities = {"ADMIN"})
    public void test3aAdminCanGetFederationRecordsThatExist() throws Exception {
        mockMvc.perform(get("/api/v1/federation/2/records"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.federationData", is("European Chess Union - ECU")))
            .andExpect(jsonPath("$.events[?(@.eventName=='European Rapid Championship 2025')].participants[*]",
                containsInAnyOrder("player3", "player4", "player5", "player6", "player7", "player9", "player10")))
            .andExpect(jsonPath("$.events[?(@.eventName=='European Coaching & Strategy Forum 2025')][0].participants", empty()));
    }


    @Test
    @Transactional
    @WithMockUser(username = "adminUser", authorities = {"ADMIN"})
    public void test3aReturns404WhenFederationNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/federation/1/records"))
	        .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/federation/9999/records")) 
            .andExpect(status().isNotFound());
    }


    @Test
    @Transactional
    @WithMockUser(username = "player1", authorities = {"PLAYER"})
    public void test3aClinicOwnersCannotGetRecords() throws JsonProcessingException, Exception{        
       mockMvc.perform(get("/api/v1/federation/1/records"))
			.andExpect(status().isForbidden());	        
    }

}