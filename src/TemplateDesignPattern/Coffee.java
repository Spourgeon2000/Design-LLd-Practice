package TemplateDesignPattern;

public class Coffee extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("Steeping the Coffee");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Coffee Beans");
    }
}
