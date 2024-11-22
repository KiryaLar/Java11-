package Multithreading;

public final class WithdrawTransaction extends Transaction{
    public WithdrawTransaction(int clientId, double amount, Currency currency) {
        super(clientId, amount, currency);
    }

    @Override
    public String toString() {
        return "WithdrawTransaction{" +
                " clientId = " + getClientId() +
                ", currency = " + getCurrency() +
                ", amount = " + getAmount() +
                " }";
    }
}
