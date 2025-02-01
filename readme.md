# An example of chat, based on Apache Cassandra with pagination
* Pagination, based on [spring-cassandra-pagination-example](https://github.com/jusexton/spring-cassandra-pagination-example)
* Creating keyspace from .cql file in `docker-entrypoint-initdb.d`, what's provided by bitnami image
* Liquibase migrations

## Links
* https://github.com/jusexton/spring-cassandra-pagination-example
* https://cassandra.apache.org/_/quickstart.html
* https://cassandra.apache.org/doc/latest/cassandra/architecture/dynamo.html
* https://stackoverflow.com/questions/60466271/cassandra-replication-in-networktopologystrategy/60476099#60476099
* https://cassandra.apache.org/doc/latest/cassandra/developing/data-modeling/data-modeling_logical.html
* https://stackoverflow.com/questions/70234153/spring-data-cassandra-not-able-to-perform-paginaton-after-passing-the-cassandra/70235061#70235061

## Perform pagination
```bash
curl -Ss 'http://localhost:8080/api/message/chat/1' | jq
curl -Ss 'http://localhost:8080/api/message/chat/1?limit=10&pagingState=000A00080000000000000009F07FFFFFF5F07FFFFFF5' | jq
```

## See keyspaces
```bash
docker exec -it cassandra-example-cassandra-1 bash
nodetool status
cqlsh -u cassandra -p cassandra
DESC KEYSPACES;
```
