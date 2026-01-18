import React, { useState, useEffect } from 'react';
import { Table } from 'reactstrap';
import tokenService from "../../services/token.service";

export default function HistoricalRecord({ id }) { 
    const [records, setRecords] = useState(null); 
    const jwt = tokenService.getLocalAccessToken();

    useEffect(() => {
        if (!id) return; 

        fetch(`api/v1/federation/${id}/records`, {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${jwt}`,
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            setRecords(data);
        })
        .catch(error => {
            console.error("Error fetching records:", error);
        });
    }, [id, jwt]);

    if (!records) {
        return <div>Cargando...</div>;
    }

    return (
        <div>
            <h1>{records.federationData}</h1>
            <Table>
                <thead>
                    <tr>
                        <th>Event name</th>
                        <th>Participants</th>
                    </tr>
                </thead>
                <tbody>
                    {records.events?.map((event, index) => (
                        <tr key={index}>
                            <td>{event.eventName}</td>
                            <td>
                                <ul>
                                    {event.participants?.map((participant, pIndex) => (
                                        <li key={pIndex}>{participant}</li>
                                    ))}
                                </ul>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
};