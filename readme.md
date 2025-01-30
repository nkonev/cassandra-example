
```bash
docker exec -it cassandra-example-cassandra-1 bash
nodetool status
cqlsh -u cassandra -p cassandra
DESC KEYSPACES;

# after a while
docker exec -it cassandra-example-cassandra-1 cqlsh
# https://cassandra.apache.org/_/quickstart.html
CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };
CREATE TABLE IF NOT EXISTS store.shopping_cart (
userid text PRIMARY KEY,
item_count int,
last_update_timestamp timestamp
);
INSERT INTO store.shopping_cart
(userid, item_count, last_update_timestamp)
VALUES ('9876', 2, toTimeStamp(now()));
INSERT INTO store.shopping_cart
(userid, item_count, last_update_timestamp)
VALUES ('1234', 5, toTimeStamp(now()));
SELECT * FROM store.shopping_cart;
```
