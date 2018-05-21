# Data Structures and Algorithms Final Project: 'MeowMeowBeenz'
The idea for this project comes from the TV show *Community*, in an episode titled "App Development and Condiments". Essentially, this app allows users to rate each other on a 1 to 5 scale. Everyone's rating is public, as are their ratings of others. The catch is that each person's ratings are weighted according to how popular they are. So for instance, a user with a 5 would get more of a vote on other peoples' ratings than someone with a 1. I aim to create this app using a SQL database to store the information; a Java server to handle user requests, access the database, and perform signal-flow graph calculations; and a Java client to send user requests.

## Contents
* [Data Structures](https://github.com/A1Liu/DSFP/blob/master/Project-Proposal.md#data-structures)
* [Roadmap](https://github.com/A1Liu/DSFP/blob/master/Project-Proposal.md#roadmap)
* [References](https://github.com/A1Liu/DSFP/blob/master/Project-Proposal.md#references)


## Data Structures
This project will employ multiple data structures to store user information and handle user requests. These include:
* Directed and undirected social graphs
  * Hashtable of nodes
  * LinkedLists of edges for each node
  * Queues for handling breadth-first traversal of the social graph
* A relational database of user information, friendships, and ratings
  * User, password, and edge tables
  * DAO's for all the tables


## Roadmap
1. [x] **A simple app for making users**
    * [x] Basic database implementation
    * [x] Basic user table and user DAO's
    * [x] Basic password salting and hashing using MD5
    * [x] Loading users from document
    * [x] Single-thread client-server connection
    * [x] Multi-thread client-server connection
    * [x] Full Client-Server-DB integration (Server Logic)
    * [x] Client UI 
    * [x] Server command prompt
2. [ ] **A simple app for making and friending users**
    * [ ] Database implementation for social graph
    * [ ] DAO's for social graph edges
    * [ ] Network handler to traverse and load social graph
    * [ ] Client actions and server logic
    * [ ] Client UI
3. [ ] **MeowMeowBeenz**
    * [ ] Database implementation for ratings graph
    * [ ] DAO's for ratings graph
    * [ ] Network handler for ratings graph
    * [ ] Network updater for server-stored ratings graph
    * [ ] Client actions and server logic
    * [ ] Client UI


## References
These are links that I found that helped me make this app. 
* [**Building a social network server**](https://github.com/speedment/speedment/wiki/Tutorial:-Build-a-Social-Network) - I didn't really use this one, but it gave me the understanding of what type of system I should be using, and also gave me a general understanding of how servers and databases are implemented.
* [**Establishing a connection to the database**](https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database) - This helped me get a working connection between the MySQL server and the Java server. Really great tutorial on how to get started with bridging the two systems.
* [**Data Access Objects Explanation**](https://stackoverflow.com/questions/19154202/data-access-object-dao-in-java) - This helped me understand how DAO's worked, and made it a lot easier to implement the DAO tutorial and expand on it.
* [**Data Access Objects Tutorial**](http://balusc.omnifaces.org/2008/07/dao-tutorial-data-layer.html) - This was really helpful for writing the UserDAO. Basically all of my code for the UserDAO and the DAOFactory came from this tutorial, although I messed around with inheritance to try to make it more modular and ended up implementing some of my own interfaces as well.
* [**Establishing a connection to server/client**](http://www.ejbtutorial.com/distributed-systems/hello-world-for-socket-programming-using-java) - I haven't used this yet
* [**Socket Programming**](https://www.javaworld.com/article/2077322/core-java/core-java-sockets-programming-in-java-a-tutorial.html) - I haven used this one yet
* [**What is a signal-flow graph?**](https://en.wikipedia.org/wiki/Signal-flow_graph) - I haven't used this yet
