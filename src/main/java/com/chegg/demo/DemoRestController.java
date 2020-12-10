package com.chegg.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

  @GetMapping("/hello/{name}")
  public ResponseEntity<String> sayHello(@PathVariable(name = "name") String name) {
    return ResponseEntity.ok("Hello " + name + ", How are you doing?");
  }
}
