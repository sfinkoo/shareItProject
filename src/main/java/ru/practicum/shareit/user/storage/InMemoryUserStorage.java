package ru.practicum.shareit.user.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.IdCreator;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapping.UserMapper;
import ru.practicum.shareit.user.model.User;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class InMemoryUserStorage implements UserStorage {

    private final IdCreator idCreator = new IdCreator();
    private final Map<Integer, User> users = new HashMap<>();
    private final UserMapper userMapper;

    @Override
    public UserDto addUser(@Valid UserDto user) {
        user.setId(idCreator.createId());
        users.put(user.getId(), userMapper.toEntity(user));
        return getUserById(user.getId());
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        User userInsert = users.put(userId, userMapper.toEntity(user));
        return userMapper.toDto(userInsert);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return users.values().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id) {
        return userMapper.toDto(users.get(id));
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

