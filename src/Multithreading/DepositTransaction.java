package Multithreading;

public final class DepositTransaction extends Transaction{

    public DepositTransaction(int clientId, double amount, Currency currency) {
        super(clientId, amount, currency);
    }

    @Override
    public String toString() {
        return "DepositTransaction{" +
                " clientId = " + getClientId() +
                ", currency = " + getCurrency() +
                ", amount = " + getAmount() +
                " }";
    }
}
