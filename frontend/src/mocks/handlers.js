import { rest } from 'msw'

const authOwner = {
    "authority": "OWNER"
};

const userAdmin1 = {
    "id": 1,
    "username": "admin1",
    "authority": {
        "authority": "ADMIN"
    }
};

const userOwner1 = {
    "id": 2,
    "username": "owner1",
    "authority": authOwner
};

const userOwner2 = {
    "id": 3,
    "username": "owner2",
    "authority": authOwner
};

const userVet1 = {
    "id": 12,
    "username": "vet1",
    "authority": {
        "authority": "VET"
    }
};

const userVet2 = {
    "id": 13,
    "username": "vet2",
    "authority": {
        "authority": "VET"
    }
};

const owner1 = {
    "id": 1,
    "firstName": "George",
    "lastName": "Franklin",
    "address": "110 W. Liberty St.",
    "city": "Sevilla",
    "telephone": "608555103",
    "plan": "PLATINUM",
    "user": userOwner1
};

const owner2 = {
    "id": 2,
    "firstName": "Betty",
    "lastName": "Davis",
    "address": "638 Cardinal Ave.",
    "city": "Sevilla",
    "telephone": "608555174",
    "plan": "PLATINUM",
    "user": userOwner2
};

const pet1 = {
    "id": 1,
    "name": "Leo",
    "birthDate": "2010-09-07",
    "type": {
        "id": 1,
        "name": "cat"
    },
    "owner": owner1
};

const pet2 = {
    "id": 2,
    "name": "Basil",
    "birthDate": "2012-08-06",
    "type": {
        "id": 6,
        "name": "hamster"
    },
    "owner": owner2
};

const vet1 = {
    "id": 1,
    "firstName": "James",
    "lastName": "Carter",
    "specialties": [],
    "user": userVet1,
    "city": "Sevilla"
}

const vet2 = {
    "id": 2,
    "firstName": "Helen",
    "lastName": "Leary",
    "specialties": [
        {
            "id": 1,
            "name": "radiology"
        }
    ],
    "user": userVet2,
    "city": "Sevilla"
};

const visit1 = {
    "id": 1,
    "datetime": "2013-01-01T13:00:00",
    "description": "rabies shot",
    "pet": pet1,
    "vet": vet1,
    "city": "Badajoz",
};

const visit2 = {
    "id": 2,
    "datetime": "2013-01-02T15:30:00",
    "description": "",
    "pet": pet1,
    "vet": vet2
};

const consultation1 = {
    "id": 1,
    "title": "Mi gato no come",
    "status": "ANSWERED",
    "owner": owner2,
    "pet": pet1,
    "creationDate": "2023-04-11T11:20:00"
};

const consultation2 = {
    "id": 2,
    "title": "Título 2",
    "status": "PENDING",
    "owner": owner1,
    "pet": pet1,
    "creationDate": "2023-04-11T11:20:00"
};

const ticket1 = {
    "id": 1,
    "description": "What vaccine should my dog recieve?",
    "creationDate": "2023-01-04T17:32:00",
    "user": userOwner1,
    "consultation": consultation1
};

const ticket2 = {
    "id": 2,
    "description": "Rabies' one.",
    "creationDate": "2023-01-04T17:36:00",
    "user": userVet1,
    "consultation": consultation1
}

const match1={
    "id": 1,
    "name": "Kasparov vs DeepBlue",
    "creator": 
        {
            "id": 1,
            "username": "Gary Kasparov"
        },
    "opponent":{
            "id": 2,
            "username": "DeepBlue"
        }
}

const match2={
    "id": 1,
    "name": "The immortal",
    "creator":
        {
            "id": 3,
            "username": "Anderssen"
        },
    "opponent":{
            "id": 4,
            "username": "Kieseritzky"
        }
}

const match3={
    "id": 1,
    "name": "Game of the Century",
    "creator":
        {
            "id": 5,
            "username": "Bobby Fischer"
        },
    "opponent":{
            "id": 6,
            "username": "Donald Byrne"
    }
}

const match4={
    "id": 1,
    "name": "The Opera Game",
    "creator": {
            "id": 7,
            "username": "Paul Morphy"
    },
    "opponent":{
            "id": 8,
            "username": "Duke Karl"
    }
}

const theImmortalGameBoard={
    "creatorTurn": false,
    "currentTurnStart": "2025-07-04T10:15:30",
    "jaque": true,
    "pieces": [
      { "xPosition": 5, "yPosition": 1, "type": "KING", "color": "WHITE" },
      { "xPosition": 4, "yPosition": 2, "type": "PAWN", "color": "WHITE" },
      { "xPosition": 7, "yPosition": 3, "type": "BISHOP", "color": "WHITE" },
      { "xPosition": 6, "yPosition": 5, "type": "QUEEN", "color": "WHITE" },
      { "xPosition": 5, "yPosition": 8, "type": "KING", "color": "BLACK" },
      { "xPosition": 6, "yPosition": 7, "type": "ROOK", "color": "BLACK" },
      { "xPosition": 8, "yPosition": 6, "type": "BISHOP", "color": "BLACK" },
      { "xPosition": 7, "yPosition": 5, "type": "PAWN", "color": "BLACK" }
    ]
  }

const theCenturyGameBoard={
    "creatorTurn": true,
    "currentTurnStart": "2025-07-04T10:30:00",
    "jaque": false,
    "pieces": [
      { "xPosition": 5, "yPosition": 1, "type": "KING", "color": "WHITE" },
      { "xPosition": 6, "yPosition": 1, "type": "ROOK", "color": "WHITE" },
      { "xPosition": 4, "yPosition": 4, "type": "QUEEN", "color": "WHITE" },
      { "xPosition": 2, "yPosition": 2, "type": "KNIGHT", "color": "WHITE" },
      { "xPosition": 5, "yPosition": 8, "type": "KING", "color": "BLACK" },
      { "xPosition": 3, "yPosition": 5, "type": "BISHOP", "color": "BLACK" },
      { "xPosition": 4, "yPosition": 5, "type": "KNIGHT", "color": "BLACK" },
      { "xPosition": 6, "yPosition": 4, "type": "BISHOP", "color": "BLACK" },
      { "xPosition": 4, "yPosition": 3, "type": "PAWN", "color": "BLACK" }
    ]
  }

const captures1 = [
  {
    "id": 5000,
    "position": null,
    "type": {
      "name": "QUEEN",
      "value": 9
    },
    "color": "BLACK",
    "captured": true
  },
  {
    "id": 5001,
    "position": null,
    "type": {
      "name": "ROOK",
      "value": 5
    },
    "color": "BLACK",
    "captured": true
  }
]

const captures2 = [
  {
    "id": 5002,
    "position": null,
    "type": {
      "name": "BISHOP",
      "value": 3
    },
    "color": "WHITE",
    "captured": true
  }
]

const mockMatches = [
    {
      id: 15,
      name: "new",
      start: "2025-10-20T12:00:00Z",
      finish: null,
      whitePlayer: { username: "player1" },
      blackPlayer: { username: "player2" },
      winner: null,
    },
    {
      id: 5,
      name: "Partida 5",
      start: "2025-10-21T09:30:00Z",
      finish: "2025-10-21T10:10:00Z",
      whitePlayer: { username: "player1" },
      blackPlayer: { username: "player2" },
      winner: { username: "player1" },
    },
  ];

export const handlers = [
    rest.get('*/api/v1/matches/5/captures', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json(captures1),
        )
    }),
    rest.get('*/api/v1/matches/6/captures', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json(captures2),
        )
    }),
    rest.get('*/api/v1/matches/1/board', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json(theImmortalGameBoard),
        )
    }),
    rest.get('*/api/v1/matches/2/board', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json(theCenturyGameBoard),
        )
    }),
    rest.get('*/api/v1/matches', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json(mockMatches),
        )
    }),
    rest.delete('*/:id', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json({
                message: "Entity deleted"
            }),
        )
    }),

    rest.get('*/api/v1/owners', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                owner1,
                owner2,
            ]),
        )
    }),

    rest.get('*/api/v1/pets', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                pet1,
                pet2,
            ]),
        )
    }),

    rest.get('*/api/v1/users', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                userAdmin1,
                userOwner1,
            ]),
        )
    }),

    rest.get('*/api/v1/vets', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                vet1,
                vet2,
            ]),
        )
    }),

    rest.get('*/api/v1/vets/specialties', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                {
                    "id": 1,
                    "name": "radiology"
                },
                {
                    "id": 2,
                    "name": "surgery"
                },
                {
                    "id": 3,
                    "name": "dentistry"
                }
            ]),
        )
    }),

    rest.get('*/api/v1/pets/:petId/visits', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                visit1,
                visit2,
            ]),
        )
    }),

    rest.get('*/api/v1/consultations', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                consultation1,
                consultation2,
            ]),
        )
    }),

    rest.get('*/api/v1/consultations/:id', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                consultation1,
            ]),
        )
    }),

    rest.get('*/api/v1/consultations/:id/tickets', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                ticket1,
                ticket2
            ]),
        )
    }),

    rest.post('*/api/v1/consultations/:id/tickets', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json(
                {
                    "id": 3,
                    "description": "test ticket",
                    "creationDate": "2023-01-04T17:32:00",
                    "user": userOwner1,
                    "consultation": consultation1
                },
            ))
    }),

    rest.put('*/api/v1/consultations/:id', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json(
                {
                    "id": 1,
                    "title": "Consulta sobre vacunas",
                    "status": "CLOSED",
                    "owner": owner1,
                    "pet": pet1,
                    "creationDate": "2023-01-04T17:30:00"
                }
            )
        )
    }),

    rest.get('*/api/v1/federation/:id/records', (req, res, ctx) => {
        const { id } = req.params;

        // Mock básico para el test
        if (id === "2") {
            return res(
                ctx.status(200),
                ctx.json({
                    federationData: "European Chess Union - ECU",
                    events: [
                        {
                            eventName: "European Rapid Championship 2025",
                            participants: ["player3", "player4", "player5", "player6", "player7", "player9", "player10"]
                        },
                        {
                            eventName: "European Coaching & Strategy Forum 2025",
                            participants: ["playerX"]
                        }
                    ]
                })
            );
        }

        return res(
            ctx.status(404),
            ctx.json({ message: "Federation not found" })
        );
    }),


]

export { mockMatches };