delete from user_role;
delete from usr;

insert into usr(id, active, password, username) values
(1, true, '$2a$08$d4p6yoRTmpFb72u4AKn.V.T3./0oKsy8i2rQzba3GrkzpTtsmfIqy', 'admin'),
(2, true, '$2a$08$d4p6yoRTmpFb72u4AKn.V.T3./0oKsy8i2rQzba3GrkzpTtsmfIqy', 'user1');

insert into user_role(user_id, roles) values
(1, 'USER'), (1 ,'ADMIN'),
(2, 'USER');

