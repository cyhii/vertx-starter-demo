package com.example.starter;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLauncher extends Launcher {

  private static final Logger log = LoggerFactory.getLogger(MyLauncher.class);

  public static void main(String[] args) {
    new MyLauncher().dispatch(args);
  }

  public static void executeCommand(String cmd, String... args) {
    new MyLauncher().execute(cmd, args);
  }

  @Override
  public void beforeStartingVertx(VertxOptions options) {
    log.info("beforeStartingVertx");
  }

  @Override
  public void afterStartingVertx(Vertx vertx) {
    log.info("afterStartingVertx");
  }

  @Override
  public void beforeStoppingVertx(Vertx vertx) {
    log.info("beforeStoppingVertx");
  }

  @Override
  public void afterStoppingVertx() {
    log.info("afterStoppingVertx");
  }
}
