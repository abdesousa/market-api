# Introduction
This is a proof of concept to validate Dropwizard framework. This project was developed by Alex Sousa (abdesousa@gmail.com)

## Assignment

Create a simple standalone Java application that provides RESTful APIs for the following operations:

1. Get a list of potato bags for sale in the market. API should accept an optional argument that specifies how many bags should be returned. If not specified, 3 bags should be returned.
2. Add a new potato bag to the market. Potato bag should be persisted for at least the application&#39;s runtime duration.

API should provide and consume the following details about the potato bags:

- ID (randomly generated string)
- Number of potatoes in a bag (minimum 1, maximum 100)
- Supplier of a bag (must be one of &quot;De Coster&quot;, &quot;Owel&quot;, &quot;Patatas Ruben&quot;, &quot;Yunnan Spices&quot;)
- Date and time when a bag was packed
- Price (number from 1 to 50)

## Additional notes

Please include a short readme file describing how to build/run project and test the APIs.

Preferred dependency management system is Maven.

Preferred framework is [Dropwizard](https://www.dropwizard.io)

Deliver the project as a git repository.

**NOTE:** Please don&#39;t share this task description on github.


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


