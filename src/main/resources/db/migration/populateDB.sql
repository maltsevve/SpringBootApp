INSERT INTO roles VALUES (1, 'ROLE_USER', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ACTIVE');
INSERT INTO roles VALUES (2, 'ROLE_MODERATOR', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ACTIVE');
INSERT INTO roles VALUES (3, 'ROLE_ADMIN', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ACTIVE');

INSERT INTO users VALUES (1, 'Admin', '$2y$10$8z6Z6doMRDhMrXlxw5pN7.9EkjQ.HWB9ipS.Z38Ky2FbsJFNNzuiy',
                          'maltsevve@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE', , 'Bhawna', 'Sehgal');

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (1, 2);
INSERT INTO user_roles VALUES (1, 3);