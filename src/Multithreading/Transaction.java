package Multithreading;

public sealed class Transaction permits DepositTransaction, WithdrawTransaction, ExchangeCurrencyTransaction, TransferTransaction{
    private final int clientId;
    private final double amount;
    private final Currency currency;

    public Transaction(int clientId, double amount, Currency currency) {
        this.clientId = clientId;
        this.amount = amount;
        this.currency = currency;
    }


    public int getClientId() {
        return clientId;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
