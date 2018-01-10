package com.example.gijsdewert.smartcare;

import java.util.Date;

/**
 * Created by gijsdewert on 10-01-18.
 */

public class Message {

    private String content;
    private String email;
    private String currentTime;

    public Message() {

    }

    public Message(String content, String email, String currentTime) {
        this.content = content;
        this.email = email;
        this.currentTime = currentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
