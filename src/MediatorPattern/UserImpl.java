package MediatorPattern;

public class UserImpl extends User {

    public UserImpl(ChatMediator chatMediator, String id, String name) {
        super(chatMediator, id, name);
    }

    public void sendMessage(String message, String userId) {
        System.out.println("Message sent by " + this.name);
        chatMediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println("Message received by " + this.name);
    }
}
