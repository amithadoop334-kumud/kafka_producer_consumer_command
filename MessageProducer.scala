package com.dev.producer

import java.util.Properties
import org.apache.kafka.clients.producer._
import scala.util.Random
/* MessageProducer - Main class - Starting point of program */
object MessageProducer {
  def main(args: Array[String]): Unit = {
    try {
      val topicName = "DEMO_TOPIC1"
      writeToKafka(topicName)

    } catch {
      case x: Exception => {
        x.printStackTrace()
        println("Exception: A number is not divisible by zero." + x)
      }
    }
  }

  def writeToKafka(topic: String): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    var key = Random.nextInt().toString+ "1"
    var message = " Hello world !! Testing Msssage " + key;

    // Message created and set topic name and key and value as message

    val record = new ProducerRecord[String, String](topic, key, message)

    // Message send to kafka producer

    val futurRecordMetadata = producer.send(record)

    println("Partition Number :" + futurRecordMetadata.get.partition());

    println("Offset Number :" + futurRecordMetadata.get.offset());
    println("Topic Name :" + futurRecordMetadata.get.topic());
    println("Timestap :" + futurRecordMetadata.get.timestamp());
    println("===========================");
    // After all process close the producer Api

    producer.close()

    print("Messge Send successfully ")

  }

}