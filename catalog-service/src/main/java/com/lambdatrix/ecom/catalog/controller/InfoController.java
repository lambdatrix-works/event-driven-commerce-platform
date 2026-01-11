package com.lambdatrix.ecom.catalog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lambdatrix.ecom.common.events.version.BuildInfo;

@RestController
@RequestMapping("/api/v1")
public class InfoController {

  @Value("${spring.application.name}")
  private String serviceName;

  @GetMapping("/info")
  public BuildInfo info() {
    return new BuildInfo(serviceName, "0.0.1-SNAPSHOT");
  }
}
