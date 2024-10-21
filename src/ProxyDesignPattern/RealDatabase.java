package ProxyDesignPattern;

public class RealDatabase implements Database{

    @Override
    public void fetchData(){
        System.out.println("Fetching sensitive data from the database.");
    }
}
