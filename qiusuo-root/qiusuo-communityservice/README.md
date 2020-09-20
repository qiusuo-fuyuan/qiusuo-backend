# Introduction

Community Service host the users, channels, etc. It only deals with
mysql database currently. Our docker will start 4 processes, but actually
only mysql is needed. To develop community service, redis, cassandra, rabbitmq
could be stopped. This will make our app use less resources.


# Technical Design Of Hibernate Fetching Lazy collections

In service, layer, in the returned data, we should initialize
all the entities, otherwise, Graphql resolvers will not be
able to find the collection values by throwing session closed.
Because the transactional annotation is on the service layer.
After the service layer, the session is reclaimed.