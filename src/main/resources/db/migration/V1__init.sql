CREATE TABLE IF NOT EXISTS shopping_cart (
    userid text,
    item_count int,
    last_update_timestamp timestamp,
    PRIMARY KEY(userid, item_count)
) WITH CLUSTERING ORDER BY (item_count ASC);

INSERT INTO shopping_cart
(userid, item_count, last_update_timestamp)
VALUES ('9876', 2, toTimeStamp(now()));
INSERT INTO shopping_cart
(userid, item_count, last_update_timestamp)
VALUES ('1234', 5, toTimeStamp(now()));
SELECT * FROM shopping_cart;
