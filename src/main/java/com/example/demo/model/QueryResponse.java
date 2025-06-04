package com.example.demo.model;

import com.fasterxml.jackson.databind.JsonNode;


public class QueryResponse {

    private ResultObj result;

    private JsonNode attachments;

    public JsonNode getAttachments() {
        return attachments;
    }

    public void setAttachments(JsonNode attachments) {
        this.attachments = attachments;
    }

    public ResultObj getResult() {
        return result;
    }

    public void setResult(ResultObj result) {
        this.result = result;
    }

    public QueryResponse(ResultObj result) {
        this.result = result;
    }
}
