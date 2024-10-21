package ChainOfResponsibilityDesignPattern;

public class DebugLogger extends Logger {
    @Override
    protected boolean canHandle(int level) {
        if (level == 3) return true;
        return false;
    }

    @Override
    protected void write(String message) {
        System.out.println("Debug: " + message);
    }
}
