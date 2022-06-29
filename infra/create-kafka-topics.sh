!#bin/bash

docker exec -it kafka_setup kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic booking-created
docker exec -it kafka_setup kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic booking_available
docker exec -it kafka_setup kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic booking_not_available
