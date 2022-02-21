# Spring-Boot-Blog
Sprint boot dependencies for spring boot starter project are SpringData JPA, Spring devtools, Spring web, Lombok (Reduces boilerplate code.)

This is a spring boot based blog application. Data is sent using Dto objects which avoids any security concerns.
Pagination and Sorting is used a lot in case of java based applications.
we can define how many items should be present in a particular page.

A particular blog can have multiple comments therefore the relationship here is one to many.

ModelMapper library is used for mapping Post and Comment objects to Dto objects. In case of the DTO objects we can pass
as much information as it is needed in case of the mapping of values.