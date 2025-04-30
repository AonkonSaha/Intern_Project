INSERT IGNORE  INTO users (id, contact, email,user_name,password,active_status) VALUES (
       1,
       '01881264859',
       'aonkon@gmail.com',
       'Aonkon Saha',
       '$2a$10$XQplSAH6HNN/dkmTyd4l4eXziO6GzLux0w9t1FaX7qynWPrus.xsa',
       0
       );
INSERT IGNORE  INTO role (id, role, description) VALUES (1, 'ADMIN', 'Secret');
INSERT IGNORE  INTO user_role(role_id,user_id) VALUES (1,1);
