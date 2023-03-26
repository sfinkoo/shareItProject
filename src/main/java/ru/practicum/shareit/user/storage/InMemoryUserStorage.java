package ru.practicum.shareit.user.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.IdCreator;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InMemoryUserStorage implements UserStorage {

    private final IdCreator idCreator = new IdCreator();
    private final HashMap<Integer, User> users = new HashMap<>();

    @Override
    public User addUser(User user) {
        user.setId(idCreator.createId());
        return users.put(user.getId(), user);
    }

    @Override
    public User updateUser(User user, Integer userId) {
        return users.put(userId, user);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserById(int id) {
        return users.get(id);
    }

    @Override
    public void deleteUserById(int id) {
        users.remove(id);
    }

    @Override
    public void deleteAllUsers() {
        if (users.isEmpty()) {
            log.info("Список пуст.");
        }
        users.clear();
    }
}

