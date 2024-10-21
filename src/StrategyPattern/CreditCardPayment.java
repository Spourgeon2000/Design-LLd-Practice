package StrategyPattern;

public class CreditCardPayment implements PaymentStrategy {

    private String creditCard;
    private String name;

    public CreditCardPayment(String creditCard, String name) {
        this.creditCard = creditCard;
        this.name = name;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Credit Card Payment was done by" + this.name + "with credit card " + this.creditCard);
    }
}
