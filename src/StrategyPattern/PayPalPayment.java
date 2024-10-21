package StrategyPattern;

public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String name;

    public PayPalPayment(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Pay Pal Card Payment was done by" + this.name + "with mail " + this.email);
    }
}
