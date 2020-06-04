The frontend send a login request to backend through url /login. The backend AuthenticationManager
will invoke the CustomAuthenticationProvider to do the login request. 
CustomAuthenticationProvider will invoke the correct AuthenticationStrategy based on the 
authenticateType. If it's github, then GithuAuthenticationStrategy is invoked. 



GithuAuthenticationStrategy will check if the user already exist in database. If it exist, then 
directly return the JWT Token to frontend.
If the user does not exist, then create this user and return the JWT token to user.