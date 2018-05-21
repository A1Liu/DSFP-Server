# Data Structures Final Project
Project to make a real life version of MeowMeowBeenz.

## Contents
* [To Do](https://github.com/A1Liu/DSFP/blob/master/README.md#todo)
* [Plan](https://github.com/A1Liu/DSFP/blob/master/README.md#plan)
* [MySQL Info](https://github.com/A1Liu/DSFP/blob/master/README.md#mysql-info)
* [Reference Links](https://github.com/A1Liu/DSFP/blob/master/README.md#reference-links)

## TODO
* Comments
  * [ ] DAO
  * [ ] Network
  * [ ] Server
    * [ ] ClientHandler
    * [ ] RequestHandler
  * [x] Users
  * [ ] SQL
* [x] Start learning SQL
* [x] Add config file support for server package  
* [x] Add admins with extra privileges  
* Add datastructure for ratings system  
* Add tons of other stuff

## Plan
#### Basics
1. [x] Create User Class with basic fields
2. [x] Create SQL Handler
3. [x] Create connection between server and user
4. [x] Create connection between server and SQL server
5. [x] Create system to log in and logout of accounts

#### Friend Network
1. Add basic searching by username
2. Add classes for user and server package to make and send undirected networks.
3. Add edge table and a way for DAOs to parse through it
4. Use breadth-first traversal to get list of friends and friends' friends, etc.

#### Ratings Network
1. Learn how to implement Signal flow graphs
2. Implement this structure first  
   1. Weight of ratings increases linearly as rating of source increases (maybe rating should be changed according to median?)
   2. Users automatically rate themselves a 5
   3. Users start with 10 ratings of 1, weight of 3, which get replaced by the first 10 ratings they get.
3. Next, implement structure above but change weight of ratings to increase quadratically as rating of source increases. Also, weight should increase for 4's and 5's if standard deviation of ratings is lower, and weight should decrease for 1's and 2's.

### Dev Cycle
1. Implement block of commands
  a. Create SQL Tables and associated DAO's
  b. Create TerminalCommand block
2. Implement GUI that reaches each part of TerminalCommand block
3. Implement ActionEvent buttons and communication to server.


## MySQL Info
IP: localhost  
Port Number: 3306  
Java Java Java Java Java Java

## Reference Links
These are links that I found that helped me make this app.  
* [I didn't use this but I might in the future](https://github.com/speedment/speedment/wiki/Tutorial:-Build-a-Social-Network)
* [Establishing a connection to the database](https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database)  
* [Data access objects explanation](https://stackoverflow.com/questions/19154202/data-access-object-dao-in-java)
* [Data access objects tutorial](http://balusc.omnifaces.org/2008/07/dao-tutorial-data-layer.html)
* [Establishing a connection to server/client](http://www.ejbtutorial.com/distributed-systems/hello-world-for-socket-programming-using-java)
* [Socket programming](https://www.javaworld.com/article/2077322/core-java/core-java-sockets-programming-in-java-a-tutorial.html)
* [What is a signal-flow graph?](https://en.wikipedia.org/wiki/Signal-flow_graph)
* [Making graphs in a RDBMS](https://www.slideshare.net/quipo/rdbms-in-the-social-networks-age/161-Thank_you_Contact_details_lorenzoibuildingscomhttpwwwalbertoninfotalks)
* [Multi-page GUI](https://stackoverflow.com/questions/14588230/javafx-app-with-many-pages)
* [Passing Variables between pages](https://stackoverflow.com/questions/14511016/how-can-i-use-a-variable-from-another-controller-in-javafx)
* [JavaFX Tutorials](https://www.youtube.com/playlist?list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG)
* [Serializable Objects and Deep Copies](http://www.cs.unc.edu/~dewan/734/current/lectures/3-Objectcomm.pdf)
* [Scene Builder app that made GUI building super easy](http://gluonhq.com/products/scene-builder/)
