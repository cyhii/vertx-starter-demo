package com.example.starter

import io.vertx.kotlin.coroutines.CoroutineVerticle

class MainVerticle : CoroutineVerticle() {

  override suspend fun start() {
    vertx.deployVerticle(MyKafkaConsumer())
  }
}
