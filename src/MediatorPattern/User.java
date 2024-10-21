package MediatorPattern;

public abstract class User {

    public String id;
    public String name;

    public ChatMediator chatMediator;


    User(ChatMediator chatMediator, String id, String name) {
        this.name = name;
        this.chatMediator = chatMediator;
        this.id = id;
    }

    public abstract void sendMessage(String message,String userId);

    public abstract void receiveMessage(String message);
}
