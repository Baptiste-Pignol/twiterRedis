package fr.epsi.tp.ws.bean;

import java.rmi.server.UID;

/**
 * Created by Baptiste on 17/03/2015.
 */

/**
 * bean witch represent a tweet
 */
public class Tweet {
    private String message, id, senderId, timestamp;

    public Tweet() {
        UID uid = new UID();
        this.id = uid.toString();
    }

    public Tweet(String id, String message, String senderId, String timestamp) {
        this.id = id;
        this.message = message;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
