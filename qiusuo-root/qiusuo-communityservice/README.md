# Introduction

Community Service host the users, channels, etc. It only deals with
mysql database currently. Our docker will start 4 processes, but actually
only mysql is needed. To develop community service, redis, cassandra, rabbitmq
could be stopped. This will make our app use less resources.