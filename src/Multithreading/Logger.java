package Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Logger implements Observer{
    private static final AtomicInteger loggerIndex = new AtomicInteger(0);
    @Override
    public void update(String message) {
        System.out.printf("Log " + loggerIndex.incrementAndGet() + ": %s%n", message);
    }
}
