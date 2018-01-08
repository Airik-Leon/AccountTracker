# Angular Account Tracker
## Authors: Airik Leon
### Date MON JAN 8 

Website home page
![Account Tracker](https://i.imgur.com/YEK8Ldu.png)
link to a Front end web App that implements: http://airikleon.io/WebApps/ReceiptTracker/index.html
link to API: http://airikleon.io:8080/ReceiptTracker/users
Front end Angular app that calls to an API hosted on AWS using a Spring & Mysql infrastructure
Technologies used: 
- Spring REST/ RESTful Services
- JPA/Hibernate
- Mysql 
- JDBC 
- AngularJS
- Jackson JSON serializer 
API information 
### GET
- http://airikleon.io:8080/users/ 
- http://airikleon.io:8080/users/{userId}
- http://airikleon.io:8080/users/{userId}/accounts
- http://airikleon.io:8080/users/{userId}/accounts/{accountId}/transactions

### POST
- http://airikleon.io:8080/users/ 
- http://airikleon.io:8080/users/{userId}/accounts
- http://airikleon.io:8080/users/{userId}/accounts/{accountId}/transactions

### PUT
- http://airikleon.io:8080/users/{userId}
- http://airikleon.io:8080/users/{userId}/accounts/{accountId}
- http://airikleon.io:8080/users/{userId}/accounts/{accountId}/transactions/{transactionId}

### DELETE
- http://airikleon.io:8080/users/{userId}
- http://airikleon.io:8080/users/{userId}/accounts/{accountId}
- http://airikleon.io:8080/users/{userId}/accounts/{accountId}/transactions/{transactionId}

DB Schema 
![DB Schema](https://i.imgur.com/OCqghLL.png)