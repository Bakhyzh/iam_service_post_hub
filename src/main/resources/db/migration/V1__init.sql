CREATE TABLE users(
                        id BIGSERIAL PRIMARY KEY ,
                        username VARCHAR(30) NOT NULL UNIQUE ,
                        password VARCHAR(80) NOT NULL ,
                        email varchar(30) UNIQUE ,
                        created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        registration_status VARCHAR(30) NOT NULL ,
                        last_login TIMESTAMP,
                        deleted BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE posts(
                        id BIGSERIAL PRIMARY KEY,
                        user_id INTEGER NOT NULL ,
                        title VARCHAR(255) NOT NULL ,
                        content TEXT NOT NULL,
                        created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        deleted BOOLEAN NOT NULL DEFAULT false,
                        likes INTEGER NOT NULL DEFAULT 0,
                        created_by VARCHAR(50),
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                        UNIQUE (title)
);
CREATE TABLE roles(
                        id SERIAL PRIMARY KEY ,
                        name VARCHAR(50) NOT NULL ,
                        user_system_role VARCHAR(64) not null ,
                        active BOOLEAN NOT NULL DEFAULT true,
                        created_by VARCHAR(50) NOT NULL
);
CREATE TABLE users_roles(
                        user_id BIGINT NOT NULL ,
                        role_id INT NOT NULL ,
                        PRIMARY KEY (user_id,role_id),
                        FOREIGN KEY (user_id) REFERENCES users (id) ,
                        FOREIGN KEY (role_id) REFERENCES roles (id)
);
INSERT INTO users (username, password, email, created, updated, registration_status, last_login, deleted) VALUES
                              ( 'super_admin', '$2a$10$HB3cog0dyiJRD9SlgHlVkOv69/dl0W.mmmq0Ufpyr8a7ncr.I529e', 'super_admin@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP, false),
                              ( 'admin', '$2a$10$Oc.zXA0sxoUKNZDaP6izg./IvW9zmXx6TLBTid81T4Vs95/8IFl/a', 'admin@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP, false),
                              ( 'user', '$2a$10$l/L1Uc7Nf8Mqy4CeC1PR1eoJ6/uwXhbje8kW8.Pp.WckrlOZCxunS', 'user@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP, false);

INSERT INTO posts (user_id,title,content,created,updated,deleted,likes) VALUES
                              (1,'First Post','this is my first post',current_timestamp,current_timestamp,false,10),
                              (1,'Second Post','this is my second post',current_timestamp,current_timestamp,false,10);
INSERT INTO roles(name, user_system_role, created_by) VALUES
                              ('SUPER_ADMIN','SUPER_ADMIN','SUPER_ADMIN'),
                              ('ADMIN','ADMIN','SUPER_ADMIN'),
                              ('USER','USER','SUPER_ADMIN');
INSERT INTO users_roles(user_id, role_id) VALUES
                              (1,1),
                              (2,2),
                              (3,3)