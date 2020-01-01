package com.dev.consumer

import java.util
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties
import scala.collection.JavaConverters._

object MessageConsumer {
  def main(args: Array[String]): Unit = {
    val topicName = "DEMO_TOPIC1"
    consumeFromKafka(topicName)
  }

  def consumeFromKafka(topic: String) = {
    try {
      val props = new Properties()
      
      props.put("bootstrap.servers", "localhost:9092")
      props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
      props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    
      props.put("auto.offset.reset", "earliest")
      // props.put("enable.auto.commit", "false");
      props.put("group.id", "LINKDIN_consumer_group1")
      props.put("max.poll.records", "2")

      val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
      
      consumer.subscribe(util.Arrays.asList(topic))

      println("Message consuming start ...");
      
      val record = consumer.poll(10000).asScala

      if (record.size > 0) {
        for (data <- record.iterator) {
          println("offset =>" + data.offset() + " ,Partition =" + data.partition() + ",data.Key=>" + data.key + ", value =" + data.value())

        }
      } else {
        println(" No Record Found ");
      }

     //  consumer.commitSync();
    } catch {
      case x: Exception => {

        println("Exception: A number is not divisible by zero.")
      }
    }
  }
}