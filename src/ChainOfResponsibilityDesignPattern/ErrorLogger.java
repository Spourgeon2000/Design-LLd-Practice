package ChainOfResponsibilityDesignPattern;

public class ErrorLogger extends Logger {
    @Override
    protected boolean canHandle(int level) {
        if (level == 2) return true;
        return false;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error: " + message);
    }
}
