package Multithreading;

public final class TransferTransaction extends Transaction {
    private final int receiverId;

    public TransferTransaction(int clientId, double amount, Currency currency, int receiverId) {
        super(clientId, amount, currency);
        this.receiverId = receiverId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    @Override
    public String toString() {
        return "TransferTransaction{" +
                " senderId = " + getClientId() +
                ", receiverId = " + receiverId +
                ", currency = " + getCurrency() +
                ", amount = " + getAmount() +
                " }";
    }
}
