import java.util.concurrent.Executor;

// Custom implementation of Executor interface
class SimpleExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        // Directly runs the command in the calling thread
        command.run();
    }
}