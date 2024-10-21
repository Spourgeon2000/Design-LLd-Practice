package StateDesignPattern;

import StrategyPattern.Item;

import java.util.List;

public class SelectionState implements State {


    public SelectionState() {
        System.out.println("Currently Vending machine is in SelectionState");
    }

    public void clickOnInsertCoinButton(VendingMachine vendingMachine) throws Exception {
        throw new Exception("you can not click on insert coin button in Selection state");
    }

    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        return;
    }

    public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
        throw new Exception("you can not insert Coin in selection state");
    }

    public void chooseProduct(VendingMachine machine, String name) throws Exception {
        Item item = machine.getInventory().getItem(name);

        int paidByUser = 0;
        for (Coin coin : machine.getCoinList()) {
            paidByUser = paidByUser + coin.value;
        }

        //3. compare product price and amount paid by user
        if (paidByUser < item.price) {
            System.out.println("Insufficient Amount, Product you selected is for price: " + item.price + " and you paid: " + paidByUser);
            refundFullMoney(machine);
            throw new Exception("insufficient amount");
        } else if (paidByUser >= item.price) {

            if (paidByUser > item.price) {
                getChange(paidByUser - item.price);
            }
            machine.setVendingMachineState(new DispenseState(machine, name));
        }
    }

    public int getChange(int returnChangeMoney) throws Exception {
        System.out.println("Returned the change in the Coin Dispense Tray: " + returnChangeMoney);
        return returnChangeMoney;
    }

    public Item dispenseProduct(VendingMachine machine, String name) throws Exception {
        throw new Exception("product can not be dispensed Selection state");
    }

    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        machine.setVendingMachineState(new IdleState(machine));
        return machine.getCoinList();
    }

    public void updateInventory(VendingMachine machine, Item item, String name) throws Exception {
        throw new Exception("Inventory can not be updated in Selection state");
    }
}
