insert into usr (id, username, password, active)
    values (1, 'admin', '$2a$08$d4p6yoRTmpFb72u4AKn.V.T3./0oKsy8i2rQzba3GrkzpTtsmfIqy', true);

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');