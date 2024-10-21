package ChainOfResponsibilityDesignPattern;

public abstract class Logger {
    public Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.canHandle(level)) {
            write(message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract boolean canHandle(int level);

    protected abstract void write(String message);
}
