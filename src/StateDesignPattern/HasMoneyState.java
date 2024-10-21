package StateDesignPattern;

import StrategyPattern.Item;

import java.util.List;

public class HasMoneyState implements State {
    public HasMoneyState() {
        System.out.println("Currently Vending machine is in HasMoneyState");
    }

    public void clickOnInsertCoinButton(VendingMachine vendingMachine) throws Exception {
        return;
    }

    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        machine.setVendingMachineState(new SelectionState());
    }

    public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
        vendingMachine.getCoinList().add(coin);
    }

    public void chooseProduct(VendingMachine machine, String name) throws Exception {
        throw new Exception("you cannot choose product in has money state");
    }

    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("you cannot get change has money state");
    }

    public Item dispenseProduct(VendingMachine machine, String name) throws Exception {
        throw new Exception("you cannot dispence product in has money state");
    }

    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        machine.setVendingMachineState(new IdleState(machine));
        return machine.getCoinList();
    }

    public void updateInventory(VendingMachine machine, Item item, String name) throws Exception {
        throw new Exception("you can not update inventory in hasMoney  state");
    }
}
