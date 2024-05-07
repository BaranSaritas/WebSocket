package com.webSocketExample.webSocketExample.controller;

import com.webSocketExample.webSocketExample.model.Message;
import com.webSocketExample.webSocketExample.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/message")
public class MessageController {

    private final MessageService service;


@PostMapping("")
    private ResponseEntity<Void> saveMessage(@RequestBody Message message) {
    service.saveMessage(message);
    return ResponseEntity.ok().build();
}


}
