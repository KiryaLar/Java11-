package Multithreading;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private final int ID;
    private ConcurrentHashMap<Currency, Double> balance = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Transaction, Integer> transactionHistory = new ConcurrentHashMap<>();
    public final Lock lock = new ReentrantLock();

    public Client() {
        ID = idGenerator.incrementAndGet();
        balance.put(Currency.RUB, 0.);
    }

    public Client(double moneyAmount, Currency currency) {
        ID = idGenerator.incrementAndGet();
        balance.put(currency, moneyAmount);
    }

    public int getID() {
        return ID;
    }

    public double getBalance(Currency currency) {
        return balance.getOrDefault(currency, 0.0);
    }

    public void updateBalance(Currency currency, double moneyAmount) {
        lock.lock();
        try {
            if (balance.containsKey(currency)) {
                balance.put(currency, balance.get(currency) + moneyAmount);
            } else balance.put(currency, moneyAmount);
        } finally {
            lock.unlock();
        }
    }

    public void printBalances() {
        lock.lock();
        try {
            System.out.println("Client ID: " + ID);
            for (ConcurrentHashMap.Entry<Currency, Double> entry : balance.entrySet()) {
                System.out.printf(" %s: %.2f%n", entry.getKey(), entry.getValue());
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
    }

}
