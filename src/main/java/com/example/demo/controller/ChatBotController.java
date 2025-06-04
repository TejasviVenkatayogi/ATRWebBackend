package com.example.demo.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChatRequestResponse;
import com.example.demo.service.ChatBotService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;


    @PostMapping
    public ChatRequestResponse chat(@RequestBody ChatRequestResponse request) {
         // Delegate to ChatBotService
        ChatRequestResponse serviceResponse = chatBotService.getFullResponse(request.getMessage());

        String message = serviceResponse.getMessage();
        JsonNode attachments = serviceResponse.getAttachments();

        // Choose constructor based on presence of attachments
        if (attachments != null) {
            return new ChatRequestResponse(message, attachments);
        } else {
            return new ChatRequestResponse(message);
        }
    }

    @PostMapping("/b")
    public ChatRequestResponse chat1(@RequestBody ChatRequestResponse request) {
        return new ChatRequestResponse(Base64.getEncoder().encodeToString(request.getMessage().getBytes(StandardCharsets.UTF_8)));
    }
}
