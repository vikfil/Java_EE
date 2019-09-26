package logic;

import java.util.List;

public interface WriteAndReadUser {
     void write(User user);
     List<User> readUser();
}
