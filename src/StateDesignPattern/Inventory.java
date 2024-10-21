package StateDesignPattern;

import StrategyPattern.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public List<Item> items = new ArrayList<>();

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.name == name) {
                return item;
            }
        }
        return null;
    }
}
