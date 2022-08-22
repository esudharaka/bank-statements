# Spring Boot + JUnit 5 + Mockito

Article link : https://www.mkyong.com/spring-boot/spring-boot-junit-5-mockito/

## 1. How to set up the project
```
a. mvn clean package
b. make sure application.properties file have the following property
   datasource.msaccess.url=jdbc:ucanaccess:///app/build/accountsdb.accdb
c. docker build -t nagarro-test . 
d. docker run -p 8080:8080 nagarro-test

```

## 2. How to generate sonar report
```
a. start sonar server
         docker run -d -p9000:9000  --platform linux/x86_64 sonarqube
b. mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=squ_dcb4473d3e3cd389b51b38939926e14def02433e
```

## 3. How to run the test cases
```
a. mvn clean test
```

## 3. How to run sample curl requests
```
curl -X POST -H "Content-Type: application/json" 
-d '{"accountNumber": "0012250016005", "fromDate": "01.06.2020", "toDate" : "30.09.2020",
 "fromAmount": "110", "toAmount": "1000"}' \
    http://admin:admin@localhost:8080/statements/v1/ -v
```

```
curl -X POST -H "Content-Type: application/json" 
 -d '{"accountNumber": "0012250016005"}' http://user:password@localhost:8080/statements/v1/ -v
```

