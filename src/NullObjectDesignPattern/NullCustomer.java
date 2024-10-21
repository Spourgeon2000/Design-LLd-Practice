package NullObjectDesignPattern;

public class NullCustomer implements Customer {
    @Override
    public String getName() {
        return "Not Available";
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
