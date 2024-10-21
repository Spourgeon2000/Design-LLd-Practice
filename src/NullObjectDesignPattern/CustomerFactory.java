package NullObjectDesignPattern;

public class CustomerFactory {
    private static final String[] customers = {"John", "Jane", "Alice"};

    public static Customer getCustomer(String name) {
        for (String customer : customers) {
            if (customer.equalsIgnoreCase(name)) {
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}