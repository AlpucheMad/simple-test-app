package com.alpuche.simpletestapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/endpoint1")
    public String endpoint1() {
        return "Respuesta desde endpoint 1";
    }

    @GetMapping("/endpoint2")
    public String endpoint2() {
        return "Respuesta desde endpoint 2";
    }
}
