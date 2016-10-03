# Isentia

Test classes to execute End to End Flow 
--------------------------------------------
 1. Crawler Application - /test/java/CrawlerServiceStandAloneTest.java
 2. Search API - /test/java/org/com/isentia/service - SearchAPIStandAloneTest.java
 
Unit Test classes
-----------------
 Path - /test/java/org/com/isentia/.
 
Implementation path
----------------------
 1. Crawler Application - Isentia/src/main/java/org/com/isentia/service/crawl/
 2. Search API - Isentia/src/main/java/org/com/isentia/service/crawl/search/
 
UML Diagrams
------------
 Isentia/UML.
 
Sequence Diagrams
-----------------
 1. Crawler Application - Isentia/Sequence_Diagram/Crawler_Sequence_Diagram.jpg.
 2. Search API - Isentia/Sequence_Diagram/SearchAPI.png.
 
Property Files
--------------
 1. Database - Isentia/src/main/resources/database.properties.
 2. Application - Isentia/src/main/resources/database.properties - Website URL to be crawled has been configured in this file. I have used   "https://www.theguardian.com/au" for this assignment.

Search APIs
-----------
 The following APIs have been built in this assignment as POC. New APIs can be implemented if requires.
 
  1. getAllArticles
  2. findArticlesByAuthor - Input is AuthorName.
  3. findAuthorByArticleName - Input is headline.
  4. findAllArticlesWithPartialName - Input is one or multiple partial headlines.
  5. findArticlesWithName - Input is one or multiple author names.
  6. findArticlesByDate - Input is Published Date


Compose.io Details
-------------------
  MongoDB path - mongodb://test:isentia123@aws-us-east-1-portal.23.dblayer.com:15732/NewsDatabase
