# Roadmap

# Synchronization of methods
Access to the ratings db CANNOT happen simultaneously
Access to UserDB and friend/follow DB is fine though, doesn't need to be synced, except in the case of 2 clients on same account.

# Processing requests

### Possible Requests
- Actions (c sends object/string, s sends confirmation string)
  - New Account (object, string)
  - Sign in (string, string)
  - Friend user (string, string)
  - rate user (string, string)
  - change account information (string/object, string)
  - delete account (string, string)
- Info Requests (c sends request string, s sends obj)
  - Public info on user (string, obj)
  - Ratings given (string, obj)
  - friends (string, obj/objs)
  - get own user info (string, obj)
  - search for users (string, objs)
  - Get other user's public info (string, obj)

### New users
1. New user clients request to reserve a username
2. server accepts or denies
3. if accept, move onto sending rest of information
4. accept information and server stores in DB

Security:MD5 salted hashing.

Super secure:  (and terrible for speed)  

user sends username, gets salt 1 and salt 2 back.  
user sends hash(salt2,hash(salt1,password)) back  
server accepts or denies based off of stored salt1, salt2, hash(salt1,password)  
server changes salt 2

Connections:  
Multiple threads

