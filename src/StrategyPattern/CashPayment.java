package StrategyPattern;

public class CashPayment implements PaymentStrategy {
    private String name;

    public CashPayment(String name) {
        this.name = name;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Cash Card Payment was done by" + this.name + "in offline");
    }
}
