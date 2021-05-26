package com.example.demo.model;


public class RSocketResponse {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RSocketResponse() {
    }

    public RSocketResponse(String data) {
        this.data = data;
    }
}
