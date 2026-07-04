package com.patterns.singleton;

public class Logger {
    private static volatile Logger instance;

    private Logger() {
        System.out.println("[Singleton] Logger Instance Initialized.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    } // <-- This closes getInstance()

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    } // <-- This closes log()

} // <-- This closes the Logger class body