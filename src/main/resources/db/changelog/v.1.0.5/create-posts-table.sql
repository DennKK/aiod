-- Создание таблицы "posts"
CREATE TABLE posts_table
(
    post_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_posts_table_user_id FOREIGN KEY (user_id) REFERENCES user_table (user_id)
);

-- Создание таблицы "tags"
CREATE TABLE posts_tags
(
    post_id BIGINT,
    tag VARCHAR(255),
    CONSTRAINT fk_posts_tags_post_id FOREIGN KEY (post_id) REFERENCES posts_table (post_id)
);