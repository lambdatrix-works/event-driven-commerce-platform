package com.lambdatrix.ecom.catalog.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lambdatrix.ecom.common.events.version.BuildInfo;

@RestController
@RequestMapping("/api/v1")
public class InfoController {

  @Value("${spring.application.name}")
  private String serviceName;

  @Autowired RedisTemplate<String, Object> redisTemplate;

  @GetMapping("/info")
  public ResponseEntity<BuildInfo> info() {
    var info = new BuildInfo(serviceName, "0.0.1-SNAPSHOT");
    return ResponseEntity.ok(info);
  }

  @PostMapping("/info-cache")
  public ResponseEntity<Void> cacheInfo(@RequestBody BuildInfo info) throws URISyntaxException {
    redisTemplate.opsForValue().set(serviceName, info);
    URI uri = new URI("http://localhost:5540");
    return ResponseEntity.created(uri).build();
  }

  @GetMapping("/info-cache")
  public ResponseEntity<BuildInfo> cacheInfoGet() throws URISyntaxException {
    BuildInfo info = (BuildInfo) redisTemplate.opsForValue().get(serviceName);
    return ResponseEntity.ok(info);
  }
}
