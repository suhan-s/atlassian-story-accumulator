package com.atlassian.acc.services;

/**
 * Created by suhan.s on 11/20/2018.
 */
public interface MessageQueue {
    void sendMessage(String message);

    String receiveMessage();
}
