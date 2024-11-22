package Multithreading;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    private final LinkedBlockingQueue<Transaction> transactionQueue = new LinkedBlockingQueue<>();
    private final ConcurrentHashMap<Currency, Double> exchangeRates = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<>();
    private final ScheduledThreadPoolExecutor rateUpdater = new ScheduledThreadPoolExecutor(1);
    private final ExecutorService cashierExecutor = Executors.newCachedThreadPool();
    private final AtomicInteger cashierIdGenerator = new AtomicInteger(0);
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    public Bank() {
        exchangeRates.put(Currency.RUB, 1.0);
        exchangeRates.put(Currency.USD, 100.03);
        exchangeRates.put(Currency.EUR, 105.73);
        exchangeRates.put(Currency.CNY, 13.77);

        rateUpdater.scheduleAtFixedRate(this::updateRates, 0, 1, TimeUnit.SECONDS);
    }

    private void updateRates() {
        exchangeRates.forEach((currency, rate) -> {
            if (!currency.equals(Currency.RUB)) {
                double delta = ThreadLocalRandom.current().nextDouble(-0.01, 0.01);
                double newRate = rate * (1 + delta);
                exchangeRates.put(currency, Double.valueOf(newRate));
            }
        });
    }

    public LinkedBlockingQueue<Transaction> getTransactionQueue() {
        return transactionQueue;
    }

    public void registerNewClient(Client client) {
        clients.put(client.getID(), client);
    }

    public Client getClient(int clientID) {
        return clients.get(clientID);
    }

    public void submitTransaction(Transaction transaction) {
        try {
            transactionQueue.put(transaction);
            System.out.println("Submitted: " + transaction);
            int cashierId = cashierIdGenerator.incrementAndGet();
            Cashier cashier = new Cashier(this, cashierId);
            cashierExecutor.execute(cashier);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Failed to submit transaction: " + transaction);
        }
    }

    public double convert(Currency fromCurrency, Currency toCurrency, double moneyAmount) {
        double fromRate = exchangeRates.getOrDefault(fromCurrency, 0.0);
        double toRate = exchangeRates.getOrDefault(toCurrency, 0.0);
        if (fromRate == 0.0 || toRate == 0.0) {
            throw new IllegalArgumentException("Invalid currency codes.");
        }
        double baseAmount = moneyAmount * exchangeRates.get(fromCurrency);
        return (baseAmount / exchangeRates.get(toCurrency));
    }

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public void shutdown() {
        rateUpdater.shutdown();
        try {
            if (!rateUpdater.awaitTermination(1, TimeUnit.SECONDS)) {
                rateUpdater.shutdownNow();
            }
        } catch (InterruptedException e) {
            rateUpdater.shutdownNow();
        }

        cashierExecutor.shutdown();
        try {
            if (!cashierExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                cashierExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            cashierExecutor.shutdownNow();
        }

        notifyObservers("Bank shutdown completed");
    }
}
