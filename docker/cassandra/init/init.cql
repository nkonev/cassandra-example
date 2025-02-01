CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = {
    'class' : 'NetworkTopologyStrategy',
    'datacenter1' : 1
} AND DURABLE_WRITES = true ;
