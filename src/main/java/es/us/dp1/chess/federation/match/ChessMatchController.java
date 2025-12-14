package es.us.dp1.chess.federation.match;


import es.us.dp1.chess.federation.user.UserService;



public class ChessMatchController {

    UserService userService;
    ChessMatchService matchService;


    public ChessMatchController(ChessMatchService service,UserService userService){
        this.matchService=service;
        this.userService=userService;    
    }

}