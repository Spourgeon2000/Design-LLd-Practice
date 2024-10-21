package StateDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private State state;
    private List<Coin> coinList;
    private Inventory inventory;

    public VendingMachine() {
        this.coinList = new ArrayList<>();
        this.inventory = new Inventory();
        this.state = new IdleState();
    }

    public State getVendingMachineState() {
        return state;
    }

    public void setVendingMachineState(State vendingMachineState) {
        this.state = vendingMachineState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }
}
