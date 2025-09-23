package com.workintech.zoo.exceptions;

public class ZooErrorResponse {
    private String message;
    private int status;
    private long timestamp;

    public ZooErrorResponse() {}

    public ZooErrorResponse(String message, int status, long timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    // test bazen bu sıralamayı da kullanır: (int, String, long)
    public ZooErrorResponse(int status, String message, long timestamp) {
        this(message, status, timestamp);
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}