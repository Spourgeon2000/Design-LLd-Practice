package StateDesignPattern;

import StrategyPattern.Item;

import java.util.List;

public class DispenseState implements State {

    DispenseState(VendingMachine machine, String name) throws Exception {

        System.out.println("Currently Vending machine is in DispenseState");
        dispenseProduct(machine, name);
    }

    public void clickOnInsertCoinButton(VendingMachine vendingMachine) throws Exception {
        throw new Exception("insert coin button can not clicked on Dispense state");
    }

    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        throw new Exception("product selection buttion can not be clicked in Dispense state");
    }

    public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
        throw new Exception("coin can not be inserted in Dispense state");
    }

    public void chooseProduct(VendingMachine machine, String name) throws Exception {
        throw new Exception("product can not be choosen in Dispense state");
    }

    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("change can not returned in Dispense state");
    }

    public Item dispenseProduct(VendingMachine machine, String name) throws Exception {
        System.out.println("Product has been dispensed");
        Item item = machine.getInventory().getItem(name);
        machine.setVendingMachineState(new IdleState(machine));
        return item;
    }

    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
        throw new Exception("refund can not be happen in Dispense state");
    }

    public void updateInventory(VendingMachine machine, Item item, String name) throws Exception {
        throw new Exception("inventory can not be updated in Dispense state");
    }
}
