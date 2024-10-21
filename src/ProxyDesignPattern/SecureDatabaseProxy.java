package ProxyDesignPattern;

public class SecureDatabaseProxy implements Database {
    private RealDatabase realDatabase;
    private String userRole;

    public SecureDatabaseProxy(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public void fetchData() {
        if (userRole.equalsIgnoreCase("ADMIN")) {
            if (realDatabase == null) {
                realDatabase = new RealDatabase();  // Only create when needed (Lazy Initialization)
            }
            realDatabase.fetchData();  // Forward request to the real object
        } else {
            System.out.println("Access Denied: You do not have permission to access this data.");
        }
    }
}
