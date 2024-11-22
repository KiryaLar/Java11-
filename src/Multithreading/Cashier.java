package Multithreading;

import java.util.InputMismatchException;

public class Cashier implements Runnable {
    private final Bank bank;
    private final int cashierId;



    public Cashier(Bank bank, int cashierId) {
        this.bank = bank;
        this.cashierId = cashierId;
    }

    @Override
    public void run() {
        bank.notifyObservers("Cashier " + cashierId + " started");
        try {
            Transaction transaction = bank.getTransactionQueue().take();
            processTransaction(transaction);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            bank.notifyObservers("Cashier " + cashierId + " interrupted");
        }
        bank.notifyObservers("Cashier " + cashierId + " finished processing");
    }

    private void processTransaction(Transaction transaction) {
        if (transaction == null) {
            System.err.println("Transaction is null");
            return;
        }

        switch (transaction) {
            case DepositTransaction depositTransaction -> {
                deposit(depositTransaction);
            }
            case WithdrawTransaction withdrawTransaction -> {
                withdraw(withdrawTransaction);
            }
            case ExchangeCurrencyTransaction exchangeTransaction -> {
                exchangeCurrency(exchangeTransaction);
            }
            case TransferTransaction transferTransaction -> {
                transferFunds(transferTransaction);
            }
            default -> bank.notifyObservers("Unexpected transaction: " + transaction);
        }
    }

    private void deposit(DepositTransaction depositTransaction) {
        Client client = bank.getClient(depositTransaction.getClientId());
        if (client != null) {
            client.lock.lock();

            try {
                Currency currency = depositTransaction.getCurrency();
                double moneyAmount = depositTransaction.getAmount();
                client.updateBalance(currency, moneyAmount);

                bank.notifyObservers("Cashier " + cashierId + " processed deposit for Client "
                        + depositTransaction.getClientId() + ": +" + depositTransaction.getAmount()
                        + " " + depositTransaction.getCurrency());
            } finally {
                client.lock.unlock();
            }
        } else {
            bank.notifyObservers("Cashier " + cashierId + ": Client not found - ID "
                    + depositTransaction.getClientId());
        }
    }

    private void withdraw(WithdrawTransaction withdrawTransaction) {
        Client client = bank.getClient(withdrawTransaction.getClientId());
        if (client != null) {
            client.lock.lock();

            try {
                Currency currency = withdrawTransaction.getCurrency();
                double moneyAmount = withdrawTransaction.getAmount();
                if (client.getBalance(currency) > moneyAmount) {
                    client.updateBalance(currency, -moneyAmount);

                    bank.notifyObservers("Cashier " + cashierId + " processed withdrawal for Client "
                            + withdrawTransaction.getClientId() + ": -" + withdrawTransaction.getAmount()
                            + " " + withdrawTransaction.getCurrency());
                } else {
                    bank.notifyObservers("Cashier " + cashierId + ": Insufficient funds for Client "
                            + withdrawTransaction.getClientId());
                }
            } finally {
                client.lock.unlock();
            }
        } else {
            bank.notifyObservers("Cashier " + cashierId + ": Client not found - ID "
                    + withdrawTransaction.getClientId());
        }
    }

    private void exchangeCurrency(ExchangeCurrencyTransaction exchangeTransaction) {
        Client client = bank.getClient(exchangeTransaction.getClientId());
        if (client != null) {
            client.lock.lock();
            try {
                Currency fromCurrency = exchangeTransaction.getCurrency();
                double moneyAmount = exchangeTransaction.getAmount();
                Currency toCurrency = exchangeTransaction.getToCurrency();

                if (client.getBalance(fromCurrency) < moneyAmount) {
                    bank.notifyObservers("Cashier " + cashierId + ": Insufficient funds for Client " + exchangeTransaction.getClientId());
                } else {
                    client.updateBalance(fromCurrency, -moneyAmount);
                    double convertedAmount = bank.convert(fromCurrency, toCurrency, moneyAmount);
                    client.updateBalance(toCurrency, convertedAmount);

                    bank.notifyObservers("Cashier " + cashierId + " processed currency exchange for Client "
                            + exchangeTransaction.getClientId() + ": " + exchangeTransaction.getAmount() + " "
                            + exchangeTransaction.getCurrency() + " -> " + convertedAmount + " "
                            + exchangeTransaction.getToCurrency());
                }
            } finally {
                client.lock.unlock();
            }
        } else {
            bank.notifyObservers("Cashier " + cashierId + ": Client not found - ID " + exchangeTransaction.getClientId());
        }
    }

    private void transferFunds(TransferTransaction transferTransaction) {
        Client sender = bank.getClient(transferTransaction.getClientId());
        Client receiver = bank.getClient(transferTransaction.getReceiverId());
        if (sender != null && receiver != null) {
            Client firstLock = sender.getID() < receiver.getID() ? sender : receiver;
            Client secondLock = sender.getID() < receiver.getID() ? receiver : sender;

            firstLock.lock.lock();
            secondLock.lock.lock();

            try {
                Currency currency = transferTransaction.getCurrency();
                double moneyAmount = transferTransaction.getAmount();
                double senderBalance = sender.getBalance(currency);

                if (senderBalance < moneyAmount) {
                    bank.notifyObservers("Cashier " + cashierId + " processed transfer from Client "
                            + transferTransaction.getClientId() + " to Client "
                            + transferTransaction.getReceiverId() + ": " + moneyAmount + " "
                            + currency);
                } else {
                    sender.updateBalance(currency, -moneyAmount);
                    receiver.updateBalance(currency, moneyAmount);

                    bank.notifyObservers("Cashier " + cashierId + " processed transfer from Client "
                            + transferTransaction.getClientId() + " to Client "
                            + transferTransaction.getReceiverId() + ": " + moneyAmount + " "
                            + currency);
                }
            } finally {
                firstLock.lock.unlock();
                secondLock.lock.unlock();
            }
        } else {
            if (sender == null) bank.notifyObservers("Cashier " + cashierId + ": Sender not found - ID "
                    + transferTransaction.getClientId());
            if (receiver == null) bank.notifyObservers("Cashier " + cashierId + ": Receiver not found - ID "
                    + transferTransaction.getReceiverId());
        }
    }

}
