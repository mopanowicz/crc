package com.example.demoapi;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class DemoApiApplication {

  public static void main(String... args) {
    Quarkus.run(args);
  }
}
