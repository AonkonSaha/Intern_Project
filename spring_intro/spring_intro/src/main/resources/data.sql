INSERT IGNORE  INTO users (id, contact, email,user_name) VALUES (1, '01881264859', 'aonkon@gmail.com','Aonkon Saha');
INSERT IGNORE  INTO role (id, role, description) VALUES (1, 'ADMIN', 'Secret');
INSERT IGNORE  INTO user_role(role_id,user_id) VALUES (1,1);
