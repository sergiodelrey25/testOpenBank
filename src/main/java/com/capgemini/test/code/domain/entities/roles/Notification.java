package com.capgemini.test.code.domain.entities.roles;

public class Notification {
    private String url;
    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Notification(String url, String body) {
        this.url = url;
        this.body = body;
    }

}
