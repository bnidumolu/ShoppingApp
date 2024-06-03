package User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticateUser {
    private Map<String, User> users = new HashMap<>();

    public void addUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
