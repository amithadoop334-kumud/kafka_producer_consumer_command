# kafka_producer_consumer_command
- kafka producer_consumer command
Today Agenda
======================================
1) kafka producer Demo through scala code 

---------------------------------------------------------------------
step1. start the zookeper server through below command 

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
----------------------------------------------------------------------

step2. start kafka  server 

.\bin\windows\kafka-server-start.bat .\config\server.properties

----------------------------------------------------------------------

step3.create topic 

.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic DEMO_TOPIC1

-----------------------------------------------------------------------

step4. consume data for testing
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic DEMO_TOPIC1 --from-beginning --consumer-property group.id=TEST_GROUP

---------------------------------------------------------------------

step5. stop kafkserver 

.\bin\windows\kafka-server-stop.bat .\config\server.properties
---------------------------------------------------------------------

Example:
==============================================
DEMOTOPIC-part1=> 0,1,2,3,4
DEMOTOPIC-part2=>0,1,2,3,4...

REPLICATION-Factor=>NODE1-> DEMOTOPIC-part1-1
                    nODE2-> DEMOTOPIC-part1-1
