# Introduction
This is a proof of concept to validate Dropwizard framework. This project was developed by Alex Sousa (abdesousa@gmail.com)

# Running The Application
## To test the example application run the following commands.
### To package the example run.
    mvn package
### To run the server run.
    java -jar target/market-api-0.0.1.jar server configuration.yml
### To hit the List PotatoesBag.
    curl -H "Content-Type: application/json" -H "Authorization: bearer test_token" -X GET http://localhost:9000/market/potatoesbag        
### To post data into the application.
    curl -H "Content-Type: application/json" -H "Authorization: bearer test_token" -X POST -d '{"potatoesQuantity":40,"supplierId":2,"packedDate":"2018-11-21","priceValue":11.12}' http://localhost:9000/market/potatoesbag


