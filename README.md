# FetchRewards

The following web service consists of the following API endpoints :



1. POST /payer/transaction : 

Request Body : { "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }

Description : add a transaction

Response Body : { "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }

curl -d  '{ "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }' -H 'Content-Type: application/json' localhost:8080/payer/transaction




2. POST /payer/spend

Request Body : { "points": 5000 }

Description : spend the points from the available balance

Response Body : 
[
    { "payer": "DANNON", "points": -100 },
    { "payer": "UNILEVER", "points": -200 },
    { "payer": "MILLER COORS", "points": -4,700 }
]

curl -d  '{ "points": 5000 }' -H 'Content-Type: application/json' localhost:8080/consumer/points



 
3. GET  /payer/balance

Description : Get the balance of each payer

Response Body : 
{
    "DANNON": 1000,
    "UNILEVER": 0,
    "MILLER COORS": 5300
}

curl -d  localhost:8080/payer/balances



How to Test ?

1. There are test cases for each of the use case. 
2. Run the application, and hit the api via Postman to validate response
3. Run the application, and hit the api via commandline. 




How to run the application on local ?

1. Maven built jar of the application is placed in the target folder with the name of RewardsSystem-0.0.1-SNAPSHOT.jar. 
2. Run the jar using the command : java -jar target/RewardsSystem-0.0.1-SNAPSHOT.jar 
3. Tomcat server will start on port 8080.




Prerequisites :

1. Java Version 8

openjdk version "1.8.0_292"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_292-b10)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.292-b10, mixed mode)




