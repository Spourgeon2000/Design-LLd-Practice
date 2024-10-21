package StateDesignPattern;

import StrategyPattern.Item;

import java.util.ArrayList;
import java.util.List;

public class IdleState implements State {

    public IdleState() {
        System.out.println("Currently Vending machine is in IdleState");
    }


    public IdleState(VendingMachine vendingMachine) {
        vendingMachine.setCoinList(new ArrayList<>());
    }

    public void clickOnInsertCoinButton(VendingMachine vendingMachine) throws Exception {
        vendingMachine.setVendingMachineState(new HasMoneyState());
    }

    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        throw new Exception("you cannot choose product in idle state");
    }

    public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
        throw new Exception("you cannot insert coin in idle state");
    }

    public void chooseProduct(VendingMachine machine, String name) throws Exception {
        throw new Exception("you cannot choose product in idle state");
    }

    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("you cannot get change in idle state");
    }

    public Item dispenseProduct(VendingMachine machine, String name) throws Exception {
        throw new Exception("you cannot dispence product in idle state");
    }

    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
        throw new Exception("you cannot get refund in idle state");
    }

    public void updateInventory(VendingMachine machine, Item item, String name) throws Exception {
        machine.getInventory().addItem(item);
    }
}
