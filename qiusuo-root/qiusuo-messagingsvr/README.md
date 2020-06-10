# Why do we break up the messaging service in its own.

(1)Messaging service does not depends on the domain user model. 
The messaging service uses redis and cassandra, rabbitmq to achieve its
functionality. The sent message already contains user name and user id
message from the backend. So it can function completely on its own.