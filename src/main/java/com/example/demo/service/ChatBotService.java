package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.model.ChatRequestResponse;
import com.example.demo.model.JWTTokenRequest;
import com.example.demo.model.JWTTokenResponse;
import com.example.demo.model.QueryRequest;
import com.example.demo.model.QueryResponse;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ChatBotService {

    @Value("${u}")
    private String username;

    @Value("${p}")
    private String password;

    @Value("${jwturl}")
    private String jwturl;

    @Value("${messageurl}")
    private String messageUrl;

    private final RestClient restClient;

    public ChatBotService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.example.com") // Update to actual base
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public ChatRequestResponse getFullResponse(String userMessage) {
        if (StringUtils.isBlank(userMessage)) {
            return new ChatRequestResponse("Empty request");
        }

        // Step 1: Get JWT Token
        String decodedPassword = new String(Base64.getDecoder().decode(password), StandardCharsets.UTF_8);
        JWTTokenRequest tokenRequest = new JWTTokenRequest(username, decodedPassword);

        JWTTokenResponse jwtResponse = restClient.post()
                .uri(jwturl)
                .body(tokenRequest)
                .retrieve()
                .body(JWTTokenResponse.class);

        // Step 2: Send message request
        QueryRequest queryRequest = new QueryRequest(List.of(userMessage), "en");

        QueryResponse queryResponse = restClient.post()
                .uri(messageUrl)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("apiToken", jwtResponse.getToken())
                .body(queryRequest)
                .retrieve()
                .body(QueryResponse.class);

        // Step 3: Inspect response
        JsonNode attachments = queryResponse.getAttachments();
        String speech = queryResponse.getResult() != null ? queryResponse.getResult().getSpeech() : null;

        // If it's an adaptive card, return only attachments
        if (attachments != null && attachments.has("type") &&
                "AdaptiveCard".equalsIgnoreCase(attachments.get("type").asText())) {
            
            return new ChatRequestResponse("", attachments); // message is null, only card sent
        }

        // Otherwise, just return the speech
        return new ChatRequestResponse(speech != null ? speech : null);
    }

    
}
