INSERT INTO Privilege (id,name) VALUES (1,'READ');
INSERT INTO Privilege (id,name) VALUES (2,'WRITE');

INSERT INTO Role (id,name) VALUES (1,'ADMIN');
INSERT INTO Role (id,name) VALUES (2,'USER');
INSERT INTO roles_privileges (role_id,privilege_id) VALUES ((select id from Role where name = "USER"), (select id from Privilege where  name = "READ"))