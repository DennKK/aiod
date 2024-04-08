-- Создание таблицы для связи "Многие ко многим" между пользователями и карточками
CREATE TABLE user_likes
(
    user_id BIGINT,
    card_id BIGINT,
    PRIMARY KEY (user_id, card_id),
    CONSTRAINT fk_user_likes_user_id FOREIGN KEY (user_id) REFERENCES user_table (user_id),
    CONSTRAINT fk_user_likes_card_id FOREIGN KEY (card_id) REFERENCES card_table (card_id)
);
