package com.example.facebook.pojo;

import java.io.Serializable;

public class FriendRequest implements Serializable {
    private String uniqueId;
    private String fromRequestId;
    private String ToRequestId;
    private String status;
    private String timestamp;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFromRequestId() {
        return fromRequestId;
    }

    public void setFromRequestId(String fromRequestId) {
        this.fromRequestId = fromRequestId;
    }

    public String getToRequestId() {
        return ToRequestId;
    }

    public void setToRequestId(String toRequestId) {
        ToRequestId = toRequestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
