package com.example.facebook.pojo;
import java.io.Serializable;
import java.util.List;

public class Action implements Serializable {
    private String channel;
    private String tag;
    private String action;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}