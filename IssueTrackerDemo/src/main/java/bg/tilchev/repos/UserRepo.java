package bg.tilchev.repos;

import bg.tilchev.models.User;
import bg.tilchev.models.UserDto;

import java.util.List;

public interface UserRepo {

    boolean persist(UserDto user);

    boolean persist(User user);

    List<UserDto> getUsersByName(String name);

    User getUserByExactName(String name);

    void closeConnection();
}
