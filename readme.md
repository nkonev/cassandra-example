
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

* https://cassandra.apache.org/doc/latest/cassandra/architecture/dynamo.html
* https://stackoverflow.com/questions/60466271/cassandra-replication-in-networktopologystrategy/60476099#60476099
* https://cassandra.apache.org/doc/latest/cassandra/developing/data-modeling/data-modeling_logical.html

```bash
curl -Ss 'http://localhost:8080/api/message/chat/1' | jq
curl -Ss 'http://localhost:8080/api/message/chat/1?limit=10&pagingState=000A00080000000000000009F07FFFFFF5F07FFFFFF5' | jq
```
