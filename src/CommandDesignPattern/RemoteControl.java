package CommandDesignPattern;

public class RemoteControl {
    private Command command;

    // Set the command (parameterized)
    public void setCommand(Command command) {
        this.command = command;
    }

    // Execute the command
    public void pressButton() {
        command.execute();
    }
}