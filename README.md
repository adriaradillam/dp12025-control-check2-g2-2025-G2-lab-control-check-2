# 🧩 Control Check 2 – Grupo G2 

> ### ⚠️ You can find the [English version here](README.en.md)

## 🧾 Enunciado

En este ejercicio, añadiremos la funcionalidad de **gestión de regulaciones y sanciones aplicadas a eventos de ajedrez**. 
Concretamente, se proporciona una clase `ChessEvent` que representa cada evento de ajedrez oficial organizado por una federación (`Federation`). Para indicar la categoría del evento organizado se utiliza el enumerado EventCategory. Los eventos pueden tener árbitros (`Referees`) asignados y estos árbitros son certificados por una federación. Cada federación define un conjunto de normas (`Rule`) oficiales bajo las que deben regirse sus eventos y de las que cada evento puede aplicar su propio subconjunto de reglas (`applies`). Cuando una regla no se cumple se aplica una sanción (`Sanction`) que es impuesta por un árbitro a un participante del evento (`User`). Para indicar los tipos de sanciones se proporciona el enumerado `SanctionType`. 

El diagrama UML que describe las clases y relaciones con las que vamos a trabajar es el siguiente: 

![Modelo de dominio](src/main/resources/images/chess-regulations.PNG)

Las clases para las que realizaremos el mapeo objeto-relacional como entidades JPA se han señalado en **rojo**. Las clases en **azul** son clases que se proporcionan ya mapeadas, pero con las que se trabajará durante el control de laboratorio.


## 🧪 Instrucciones

La primera tarea en este control será clonar este repositorio. Si está utilizando los ordenadores del aula, tendrá que usar un token de autenticación de GitHub como contraseña. En el propio repositorio se incluye un documento de ayuda que explica cómo configurar dicho token. A continuación, deberá importar el proyecto en su entorno de desarrollo preferido y comenzar a trabajar en los ejercicios que se detallan más abajo. 

La funcionalidad implementada en cada uno de los ejercicios será validada mediante pruebas unitarias. Las pruebas pueden ejecutarse desde su IDE o utilizando:

```bash
./mvnw test
```

Cada ejercicio vale **2 puntos**. En número de casos de prueba varía de un ejericio a otro. Su nota en cada ejericio dependerá del porcentaje de tests que pasan (no fallan). Por ejemplo:

| Test que pasan | Nota |
|---------------|--------|
| 100% | 2.0 |
| 50% | 1.0 |
| 10% | 0.2 |



### 📤 Envío

La entrega de este control se realizará en **dos pasos:**
1. **Suba la actividad en la plataforma EV asociada al control, proporcionando como texto la URL de su repositorio personal.** Puede hacerlo al inicio de la sesión.
2. **Realice un único *git push* a su repositorio individual** (el que contiene este documento) una vez que haya completado todos los ejercicios.

Recuerde que **debe hacer *push* de su trabajo antes de cerrar sesión en el ordenador y abandonar el aula;** de lo contrario, su intento será calificado como no entregado. 



### ⚠️ Notas importantes

1. **No cambie los nombres de las clases, los nombres de los métodos, los tipos de retorno ni los parámetros.**  
   Las pruebas dependen de que las firmas sean exactamente las mismas.
2. **No modifique las pruebas unitarias.**  
   Cualquier cambio local en las pruebas será ignorado; su repositorio se volverá a evaluar desde cero.
3. Mientras los ejercicios no estén completos, **algunas pruebas fallarán** — esto es lo esperado.  
   Solo los proyectos con una calificación de 10 puntos ejecutan todas las pruebas sin fallos.
4. **El uso de Git forma parte de la evaluación.** No se permite pedir ayuda sobre el uso de Git o GitHub.
5. Los proyectos que **no compilen o no arranquen Spring** recibirán **0 puntos**.
6. Excepto el **Ejercicio 2** (depende del 1), los ejercicios son **independientes**. Puedes resolverlos en cualquier orden.

`

## 🧩 Ejercicio 1 – Creación de las entidades `Referee` y `Sanction`, sus repositorios asociados y relaciones. 

### Parte 1.a - Referee y Sanction y sus repositorios asociados (1 punto)

Modifique las clases `Referee` y `Sanction` para que sean entidades. Estas clases están alojadas en el paquete `es.us.dp1.chess.federation.regulation` y debe tener los siguientes atributos. 

Para la clase `Referee`: 

* El atributo de tipo entero (`Integer`) llamado '**id**' actuará como clave primaria en la tabla de la base de datos relacional asociada a la entidad.
* El atributo de tipo cadena de caracteres (`String`) llamado '**name**' que debe tener un mínimo de 3 caracteres y un máximo de 50 caracteres. 
* El atributo de tipo cadena de caracteres (`String`) obligatorio llamado '**licenseNumber**', no puede ser vacío ni debe permitir espacios en blanco y debe tener una longitud de 10 caracteres exactos. 
* El atributo de tipo fecha (`LocalDate`) llamado '**certificationDate**' que representa la fecha a partir de la cual el árbitro fue certificado y es un atributo obligatorio. 

Para la clase `Sanction`: 
* El atributo de tipo entero (`Integer`) llamado '**id**' actuará como clave primaria en la tabla de la base de datos relacional asociada a la entidad.
* El atributo de tipo cadena de caracteres (`String`) obligatorio llamado '**description**, no puede ser vacío ni debe permitir espacios en blanco y debe tener una longitud de mínima de 15 caracteres y máxima de 70 caracteres. 
* El atributo de tipo enumerado 'SanctionType' llamado '**type**' que indica el tipo de sanción. Este atributo es obligatorio y debe almacenarse como una cadena en la BD ('WARNING', 'PENALTY_TIME', 'MATCH_LOST', 'EXPULSION'). 
* Un atributo de tipo `double`, opcional, llamado '**monetaryFine**' que indica la cantidad de multa monetaria que deberá pagar una persona a la que se le haya aplicado esta sanción por lo que debe ser un valor mayor que cero. 

No modifique por ahora las anotaciones `@Transient` de las clases. 
Modifique las interfaces `RefereeRepository` y `SanctionRepository` alojadas en el paquete `es.us.dp1.chess.federation.regulation` respectivamente para que extiendan de `CrudRepository`. No olvide especificar sus parámetros de tipo. 


### Parte 1.b - Creación de relaciones entre las entidades (1 punto)

Elimine las anotaciones `@Transient` de los métodos y atributos que las tengan en las entidades creadas en el ejercicio anterior (`Referee` y `Sanction`). Se pide crear las siguientes relaciones entre las entidades:

Cree dos relaciones unidireccionales desde `Referee` una hacia `Federation` usando el atributo `certifiedBy` y otra hacia `ChessEvent` usando el atributo `assignedTo` que expresen las relaciones que aparecen en el diagrama UML (mostrado en la primera página de este enunciado). 
Cree tres relaciones unidireccionales desde `Sanction` una hacia `Referee` usando el atributo `imposedBy`, otra hacia `User` usando el atributo `imposedOn` y otra hacia `Rule` usando el atributo `ruleBroken` que expresen las relaciones que aparecen en el diagrama UML (mostrado en la primera página de este enunciado). 
Debe asegurarse de que todas las relaciones expresan adecuadamente la cardinalidad que muestra el diagrama UML considerando que los atributos pueden ser nulos puesto que la cardinalidad en el extremo navegable es `0..1`.


## 🧩 Ejercicio 2 – Modificación del script de inicialización de la base de datos (2 puntos)

Modifique el script de inicialización de la base de datos para que se creen los siguientes árbitros (`Referee`) y sanciones (`Sanction`): 

### Referees:

#### Referee 1
```text
Id: 1
Nombre: “John Peterson”
Número de licencia: “REF2025001”
Fecha de certificación: 6 de enero de 2000
```
* El árbitro ha sido certificado por la federación cuyo id es 2 (`Federation`). 

#### Referee 2
```
Id: 2
Nombre: “María González”
Número de licencia: “REF2025002”
Fecha de certificación: 14 de diciembre de 1998
```
 * El árbitro ha sido certificado por la federación cuyo id es 3 (`Federation`). 

### Sanctions

#### Sanction 1
```
Id: 1
Descripción: 'Unsportsmanlike conduct. Disrespect toward an opponent.'
Multa monetaria (monetary fine): 500.0
Tipo de sanción: 'EXPULSION'
```
 * La sanción ha sido impuesta por el árbitro cuyo id es 2 (`Referee`). 
 * La sanción ha sido impuesta al jugador cuyo id es 9 (`User`).
 * La sanción ha sido aplicada por romper la regla cuyo id es 36 (Rule). 

#### Sanction 2
```
Id: 2
Descripción: 'The player arrived late to the playing area.'
Tipo de sanción: 'WARNING'
```
* La sanción ha sido impuesta por el árbitro cuyo id es 1 (`Referee`). 
* La sanción ha sido impuesta al jugador cuyo id es 6 (`User`). 
* La sanción ha sido aplicada sin estar asociada a ninguna regla. 


**Además**, deberá registrar árbitros (`Referee`) a los eventos de ajedrez como sigue (`ChessEvent`): 
* Los eventos con ids 3 y 4 tienen asignados los árbitros con ids 1 y 2. 
* Los eventos con ids 5 y 6 tienen asignados el árbitro cuyo id 1. 
* El evento con id 7 tiene asignado el árbitro cuyo id es 2. 


> ⚠️ **Recuerde:** el orden en la inserciones es importante para el correcto funcionamiento del script de inicialización de base de datos, especialmente cuando se insertan los datos relativos a las relaciones entre las entidades.



## 🧩 Ejercicio 3 – Creación y modificación de un `controlador` y componente `frontend` de visualización del registro histórico de los eventos de ajedrez. 

### Parte 3.a - Creación de un controlador para consultar el registro histórico de los eventos de una federación (1 punto)

Modifique la clase `HistoricalRecordController` para que responda a peticiones tipo `GET` en la url: 
```bash 
http://localhost:8080/api/v1/federation/{idFederation}/records 
```

Para ello, el controlador debe usar el servicio de gestión de federaciones (`FederationService`) y el servicio de gestión de eventos (`ChessEventService`). 
Es importante que dicho controlador devuelva los datos de la federación solicitada 
(`<nombre> - <acronym>`), los nombres de los eventos de esa federación y los participantes (`<username>`) de cada evento en el siguiente formato: 

```json
{
  "federationData": "European Chess Union - ECU",
  "events": [
    {
      "eventName": "European Rapid Championship 2025",
      "participants": ["player3", "player4", "player5", "player6", "player7", "player9", "player10"]
    },
    ...
    {
      "eventName": "European Coaching & Strategy Forum 2025",
      "participants": null
    }
    ...
  ]
}
```

#### Requisitos:
Este endpoint de la API debería estar accesible únicamente para usuarios de tipo `ADMIN`. Si se pide el registro histórico de eventos de una federación que no está registrada en la base de datos, deberá devolver el código de estado `404 (NOT_FOUND)`.




### Parte 3.b - Creación de un componente frontend para la visualización del registro histórico de los eventos de una federación (1 punto)

Modifique el componente React proporcionado en el fichero `frontend/src/federations/records/index.js` para que muestre el listado de eventos de una federación y los participantes de cada evento.

Este componente deberá tomar como propiedad llamada `id` el identificador de la federación para la que se debe mostrar el registro histórico de eventos. 

Para obtener la información, debe hacer uso de la API lanzando una petición de tipo `GET` contra la URL `api/v1/federation/{idFederation}/records`. Para realizar esta petición se recomienda usar la función `fetch`, evitando el uso de `axios` o equivalente.

Tras la llamada a la API, el componente debe mostrar:
* los datos de la federación (`<nombre> - <acronym>`) como título de nivel 1 (`<h1>`) 
* una tabla que incluya:
  * una columna para el nombre del evento llamada `Event name`
  * otra columna para los participantes del evento llamada `Participants`. Para esto, el componente debe mostrar en la celda asociada una lista (no ordenada `<ul>`) con los nombres de usuario de los participantes en cada evento. 

> Para poder lanzar este test y comprobar su resultado puede colocarse en la carpeta frontend y ejecutar el comando `npm test` y pulsar 'a' en el menú de comandos de `jest`. Nótese que previamente debe haber lanzado al menos una vez el comando npm install para que todas las librerías de node estén instaladas. 

## 🧩 Ejercicio 4 – Anotar el repositorio de eventos de ajedrez (ChessEvent) con una consulta compleja (2 puntos). 

Modifique la consulta personalizada que puede invocarse a través del método `findUsersWithLowUpcomingParticipations` del repositorio de eventos `ChessEventRepository` (alojado en el `paquete es.us.dp1.chess.federation.regulation`) que reciba como parámetro un conjunto de federaciones, una fecha a partir de la cual se quieren consultar los eventos y un número entero que representa el número mínimo de participaciones de los usuarios en los eventos.

El objetivo es que devuelva todos los usuarios que han participado un número de veces menor al entero dado en los eventos organizados por el conjunto de federaciones dadas y que tienen lugar en una fecha posterior a la dada como parámetro. 

Este método permitirá identificar participantes registrados, pero con un bajo número de participaciones previstas en eventos futuros, para enfocar las campañas de marketing y motivar a la participación.

![Test4](src/main/resources/images/test4.jpg)

Supongamos que invocamos el método con los siguientes valores de los parámetros:
* federaciones = {2, 3, 4}
* fecha= '01-08-2025'
* el entero dado = 4

El resultado debería de ser el conjunto de usuarios (`User`) que estén asociados a menos de tres  eventos de entre los organizados por las federaciones 2, 3, 4 (filas de las 2 a la 17) con fecha mayor que la dada (filas: 2, 3, 4, 6, 7, 8, 9, 14, 15, 16). Estos serían los usuarios `player1`, `player5`, `player8` y `player9` que aparecen (3, 2, 2 y 3 veces, respectivamente). 

Finamente, cree un método en la clase `ChessEventService` llamado `getUsersWithLowUpcomingParticipations` que reciba los parámetros `federations`, `date` y `numParticipations` (en ese orden), para que realice una llamada al método del repositorio y devuelva el resultado obtenido por este. 



## 🧩 Ejercicio 5 – Implementar una prueba para un algoritmo de validación de periodicidad de los eventos de ajedrez de una federación (2 puntos). 

Las federaciones de ajedrez han decidido crear un sistema informático que permita validar la periodicidad entre sus eventos oficiales (`ChessEvent`), garantizando que no se celebren eventos demasiados próximos en el tiempo. 

Para ello, se ha definido un algoritmo que recibe como parámetros: 
* El *nuevo evento* `ChessEvent` que se desea registrar.
* El *último evento registrado* `ChessEvent` con el que se debe comparar
* El *número mínimo de días* que debe existir entre ambos eventos. 

El algoritmo debe comparar que la *diferencia de días* entre el último evento registrado y el nuevo evento es *estrictamente mayor* que el número de días dado como parámetro. 
* Si los eventos son de la misma categoría y federación y la diferencia de días entre ellos es menor o igual que el valor indicado deberá lanzar la excepción del tipo `InvalidPeriodicityForANewChessEvent`. 
* En cualquier otro caso, no debe lanzarse ninguna excepción. 

La interfaz del algoritmo está definida en la interfaz `PeriodicityEventsAlgorithm` que se encuentra en el paquete `es.us.dp1.chess.federation.regulation.periodicity`. Se proporcionan *seis implementaciones* de dicha interfaz: una correcta y cinco incorrectas. 

**Trabajo a realizar**

Modifique la clase de pruebas llamada `PeriodicityEventsAlgorithmTest` que se encuentra en el paquete `es.us.dp1.chess.federation.periodicity` e implemente tantos métodos de prueba como considere necesarios para validar el funcionamiento correcto del algoritmo. 

La clase de pruebas tiene un atributo de tipo `PeriodicityEventsAlgorithm` llamado `algorithm`, que debe utilizarse como sujeto bajo prueba en todos los tests. También proporciona un método llamado `createEvent`que puede usar para crear instancias de `ChessEvent` fácilmente.

Su implementación del test **no debe usar mocks, ni anotaciones de pruebas de Spring** (`@DataJpaTest`, `@SpringBootTest`, etc.), ni tests parametrizados, y todos los métodos anotados con `@Test` deben ser sin parámetros.

---


<div align="center">
<b>Diseño y Pruebas I -- Diciembre, 2025 </b>
</div>



