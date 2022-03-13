# Spring-Boot-Blog
Sprint boot dependencies for spring boot starter project are SpringData JPA, Spring devtools, Spring web, Lombok (Reduces boilerplate code.)

This is a spring boot based blog application. Data is sent using Dto objects which avoids any security concerns.
Pagination and Sorting is used a lot in case of java based applications.
we can define how many items should be present in a particular page.

A particular blog can have multiple comments therefore the relationship here is one to many.

ModelMapper library is used for mapping Post and Comment objects to Dto objects. In case of the DTO objects we can pass
as much information as it is needed in case of the mapping of values.
When we add the spring-boot-starter-security dependency then we will get a default username and password menu which can be later
used for other purposes.
For enabling the basic authentication in the api we need to add some information to the controller. If the api is then accessed without the username and password then it will cause some issues.
Advanced configuration has been created as per our requirement where all the roles and the values are defined.

Spring security can be used for authentication of the routes there are two ways this is done one is using the inmemory structures 
while the other way is storing information inside the data structures.