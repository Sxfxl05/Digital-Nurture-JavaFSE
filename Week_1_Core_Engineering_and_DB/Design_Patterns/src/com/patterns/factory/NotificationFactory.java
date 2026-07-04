package com.patterns.factory;
public class NotificationFactory {
    public Notification createNotification(String channel) {
        if ("SMS".equalsIgnoreCase(channel)) return new SMSNotification();
        return null;
    }
}
