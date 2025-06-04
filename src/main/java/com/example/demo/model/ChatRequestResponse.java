package com.example.demo.model;

import com.fasterxml.jackson.databind.JsonNode;

public class ChatRequestResponse {

    private String message;
    private JsonNode attachments;

    

    public ChatRequestResponse() {
    }

    

    public ChatRequestResponse(String message, JsonNode attachments) {
        this.message = message;
        this.attachments = attachments;
    }

    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatRequestResponse(String message) {
        this.message = message;
    }



    public JsonNode getAttachments() {
        return attachments;
    }



    public void setAttachments(JsonNode attachments) {
        this.attachments = attachments;
    }
}
