INSERT INTO users (password,username) VALUES ('$2a$12$ylu1RX5i1U0h/MipWXtjsefe4hWIjVPLBcw0VM4UsY65RgJqluJ/C','admin');
INSERT INTO roles (name) VALUES ('ADMIN' );

INSERT INTO users_roles (user_id,role_id) VALUES ( 1, 1);