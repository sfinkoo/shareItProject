package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Component
public interface UserStorage {

    User addUser(User user);

    User updateUser(User user, Integer id);

    List<User> getAllUsers();

    User getUserById(int id);

    void deleteUserById(int id);

    void deleteAllUsers();

}
