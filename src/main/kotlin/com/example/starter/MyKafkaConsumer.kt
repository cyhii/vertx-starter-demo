package com.example.starter

import io.vertx.core.json.JsonObject
import io.vertx.kafka.client.consumer.KafkaConsumer
import io.vertx.kafka.client.consumer.KafkaConsumerRecord
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MyKafkaConsumer : CoroutineVerticle() {

  private val log: Logger = LoggerFactory.getLogger(javaClass)

  override suspend fun start() {
    val kafkaConfig: Map<String, String> = mapOf(
      "bootstrap.servers" to "127.0.0.1:9092",
      "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" to "io.vertx.kafka.client.serialization.JsonObjectDeserializer",
      "group.id" to "my-consumer",
      "auto.offset.reset" to "latest",
      "enable.auto.commit" to "true",
    )
    val consumer: KafkaConsumer<String, JsonObject> = KafkaConsumer.create(vertx, kafkaConfig)

    consumer.handler { record ->
      consumerHandler(record)
    }

    consumer.exceptionHandler {
      // WARN: uncomment this line below then you will get log storm
      // log.warn("failed to consume message : {}", it.message, it)
    }

    val topics = setOf("my-topic")
    consumer.subscribe(topics) {
      if (it.succeeded()) {
        log.info("succeeded to subscribe topics {}", topics)
      } else {
        log.error("failed to subscribe topics {}", topics, it.cause())
      }
    }
  }

  private fun consumerHandler(record: KafkaConsumerRecord<String, JsonObject>) {
    val json = record.value()

    log.info("consume message {}", json)
    // consume this message
  }
}
