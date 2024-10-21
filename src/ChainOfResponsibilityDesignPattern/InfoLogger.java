package ChainOfResponsibilityDesignPattern;

public class InfoLogger extends Logger {

    @Override
    protected boolean canHandle(int level) {
        if (level == 1) return true;
        return false;
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}
