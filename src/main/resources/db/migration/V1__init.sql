CREATE TABLE posts(
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL ,
                        content TEXT NOT NULL,
                        created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        likes INTEGER NOT NULL DEFAULT 0,
                        UNIQUE (title)
);

INSERT INTO posts (title,content,created,likes) VALUES
                              ('First Post','this is my first post',current_timestamp,10),
                              ('Second Post','this is my second post',current_timestamp,10);
