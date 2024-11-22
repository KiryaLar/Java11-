package Multithreading;

public final class ExchangeCurrencyTransaction extends Transaction{
    private final Currency toCurrency;

    public ExchangeCurrencyTransaction(int clientId, double amount, Currency fromCurrency, Currency toCurrency) {
        super(clientId, amount, fromCurrency);
        this.toCurrency = toCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    @Override
    public String toString() {
        return "ExchangeCurrencyTransaction{" +
                " clientId = " + getClientId() +
                ", fromCurrency = " + getCurrency() +
                ", toCurrency = " + toCurrency +
                ", amount = " + getAmount() +
                " }";
    }
}
