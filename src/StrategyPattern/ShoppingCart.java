package StrategyPattern;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void pay(PaymentStrategy paymentStrategy) {
        paymentStrategy.pay(calculate());
        System.out.println("Total Amount Paid is " + calculate());
    }

    private int calculate() {
        int total = 0;
        for (Item item : items) {
            total = total + item.price;
        }
        return total;
    }
}
