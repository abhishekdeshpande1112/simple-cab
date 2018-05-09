## Overview
This is an API that returns trips a particular cab (medallion) has made given a particular pickup date.

## Implementation
The solution delivered here is a Java project implemented as a Spring Boot / Maven project.


## Building the program
In order to build the program, the following is required

- Java 8 JDK
- Maven 3.3.x

In order to start a project unzip and open this project to your development machine then at the top-level directory type:
$ mvn clean install


## Running the program in local mode
After building the application you can run the service by performing the following steps:
Database used is MySql. Its expected the sql data is in the database.

1. Start the Spring Boot server -> Server.java which runs on 
http://localhost:8080/

2) Run the client -> Client.java

Now the service is available at:
- Url: http://localhost:8080/cab/trips
- Method: POST
- Content/Type: application/json

##Test Sample
**Request**
URL
http://localhost:8080/cab/trips

**Body** 
{
	"medallions": ["0C107B532C1207A74F0D8609B9E092FF","B672154F0FD3D6B5277580C3B7CBBF8E"],
	"pickupDate": "2013-12-01"
}

**Response**
[
    {
        "medallion": "0C107B532C1207A74F0D8609B9E092FF",
        "numberOfTrips": 1
    },
    {
        "medallion": "B672154F0FD3D6B5277580C3B7CBBF8E",
        "numberOfTrips": 1
    }
]

## Additional Notes
*** Key features ***
- Basic Spring caching is used

Some tools used for speeding up the development:
- https://start.spring.io/ for spring boot app structure
- Standard development practice that I normally follow at work is used.

## Improvements TODO
- Can use some security methods.
- Caching without TTL is used. We can use EHCache's configuration to configure the TTL
- Integration test is done based on existing data (which might fail if data not present or tweaked)