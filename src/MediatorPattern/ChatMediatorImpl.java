package MediatorPattern;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator {

    List<User> users = new ArrayList<>();

    public void sendMessage(String message, User user) {
        for (User user1 : users) {
            if (user.id != user1.id) {
                user1.receiveMessage(message);
            }
        }
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
