# -- USERS
# INSERT INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
# VALUES (
#            53,
#            'Aonkon Saha',
#            'aonkon@gmail.com',
#            '01712524864',
#            '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
#            'Male',
#            '1999-09-14',
#            0
#        )
# ON DUPLICATE KEY UPDATE
#                      name = VALUES(name),
#                      email = VALUES(email),
#                      contact = VALUES(contact),
#                      password = VALUES(password),
#                      gender = VALUES(gender),
#                      date_of_birth = VALUES(date_of_birth),
#                      is_active = VALUES(is_active);
#
# -- USER ROLES
# INSERT INTO user_roles (id, role)
# VALUES (53, 'PATIENT')
# ON DUPLICATE KEY UPDATE
#     role = VALUES(role);
#
# INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (53, 53);
#
# INSERT INTO patients (id, address, date_of_birth, patient_name, profile_picture_url, user_id)
# VALUES (
#            20,
#            'Mirpur-2,Dhaka,Bangladesh',
#            '1999-09-14',
#            'Aonkon Saha',
#            '/images/default_profile.jpg',
#            53
#        )
# ON DUPLICATE KEY UPDATE
#                      address = VALUES(address),
#                      date_of_birth = VALUES(date_of_birth),
#                      patient_name = VALUES(patient_name),
#                      profile_picture_url = VALUES(profile_picture_url),
#                      user_id = VALUES(user_id);
