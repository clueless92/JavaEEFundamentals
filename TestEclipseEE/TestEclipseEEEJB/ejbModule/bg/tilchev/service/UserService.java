package bg.tilchev.service;

import java.util.List;

import bg.tilchev.entity.User;

public interface UserService {

    List<User> findAllUsers();

    User save(User entity);

    User update(User entity);

    void delete(User entity);

    User findById(Long id);

    User loginUser(String aUsername, String aPassword);

    User checkUserExists(String username, Long id);

	List<User> findAllUsersAndPostsCount();
}