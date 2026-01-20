package com.lambdatrix.ecom.catalog.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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

  @Value("${kafka.topic.name}")
  private String topicName;

  @Autowired RedisTemplate<String, Object> redisTemplate;

  @Autowired KafkaTemplate<String, Object> kafkaTemplate;

  @GetMapping("/info")
  public ResponseEntity<BuildInfo> info() {
    var info = new BuildInfo(serviceName, "0.0.1-SNAPSHOT");
    return ResponseEntity.ok(info);
  }

  @PostMapping("/cache-info")
  public ResponseEntity<Void> cacheInfo(@RequestBody BuildInfo info) throws URISyntaxException {
    redisTemplate.opsForValue().set(serviceName, info);
    URI uri = new URI("http://localhost:5540");
    return ResponseEntity.created(uri).build();
  }

  @GetMapping("/cache-info")
  public ResponseEntity<BuildInfo> cacheInfoGet() throws URISyntaxException {
    BuildInfo info = (BuildInfo) redisTemplate.opsForValue().get(serviceName);
    return ResponseEntity.ok(info);
  }

  @PostMapping("/kafka-info")
  public ResponseEntity<Void> kafkaInfo(@RequestBody BuildInfo info) throws URISyntaxException {
    kafkaTemplate.send(topicName, info);
    return ResponseEntity.accepted().build();
  }

  @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
  public void onMessage(BuildInfo info) {
    System.out.println("Consumed: " + info);
  }
}
