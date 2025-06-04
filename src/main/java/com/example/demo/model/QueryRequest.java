package com.example.demo.model;

import java.util.List;


public class QueryRequest {

    private List<String> query;
    private String lang;

    public List<String> getQuery() {
        return query;
    }

    public void setQuery(List<String> query) {
        this.query = query;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public QueryRequest(List<String> query, String lang) {
        this.query = query;
        this.lang = lang;
    }
}
