package com.patterns.factory;
class SMSNotification implements Notification {
    @Override public void notifyUser() { System.out.println("[Factory] Sending an SMS notification..."); }
}
