package org.example;

import org.example.controller.Controller;

import java.util.List;

public class Appliccation {
  private final List<Controller> controllers;

  public Appliccation(List<Controller> controllers) {
    this.controllers = controllers;
  }

  public void start() {
    for (Controller controller : controllers) {
      controller.initializeEndpoints();
    }
  }
}
