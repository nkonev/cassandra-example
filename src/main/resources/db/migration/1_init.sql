CREATE TABLE IF NOT EXISTS message (
    chat_id bigint,
    id bigint,
    create_date_time timestamp,
    content text,
    owner_id bigint,
    PRIMARY KEY(chat_id, id)
) WITH CLUSTERING ORDER BY (id ASC);
