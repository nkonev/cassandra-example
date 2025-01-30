
```bash
# after a while
docker exec -it cassandra-example-cassandra-1 cqlsh
# https://cassandra.apache.org/_/quickstart.html
CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };
CREATE TABLE IF NOT EXISTS store.shopping_cart (
userid text PRIMARY KEY,
item_count int,
last_update_timestamp timestamp
);
  
SELECT * FROM store.shopping_cart;
```
