package Multithreading;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        bank.addObserver(new Logger());

        Client client1 = new Client(100, Currency.USD);
        Client client2 = new Client(200_000, Currency.RUB);

        bank.registerNewClient(client1);
        bank.registerNewClient(client2);

        client1.updateBalance(Currency.EUR, 100);
        client2.updateBalance(Currency.RUB, 1_000_000);

        System.out.println("---------------");
        client1.printBalances();
        client2.printBalances();

        DepositTransaction deposit = new DepositTransaction(client1.getID(), 100, Currency.EUR);
        WithdrawTransaction withdraw = new WithdrawTransaction(client2.getID(), 50_000, Currency.RUB);
        ExchangeCurrencyTransaction exchange = new ExchangeCurrencyTransaction(client1.getID(), 50, Currency.EUR, Currency.CNY);
        TransferTransaction transfer = new TransferTransaction(client1.getID(), 50, Currency.USD, client2.getID());

        System.out.println("---------------");
        bank.submitTransaction(deposit);
        Thread.sleep(1000);
        client1.printBalances();

        System.out.println("---------------");
        bank.submitTransaction(withdraw);
        Thread.sleep(1000);
        client2.printBalances();

        System.out.println("---------------");
        bank.submitTransaction(exchange);
        Thread.sleep(1000);
        client1.printBalances();

        System.out.println("---------------");
        bank.submitTransaction(transfer);
        Thread.sleep(1000);
        client1.printBalances();
        client2.printBalances();

        bank.shutdown();
    }
}
