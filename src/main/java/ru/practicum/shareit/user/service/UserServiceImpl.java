package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ResourceException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapping.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserStorage userStorage;
    private final UserMapper userMapper;

    @Override
    public UserDto addUser(User user) throws ValidationException {
        checkUniqueEmail(user.getEmail());
        userStorage.addUser(user);
        log.debug("Пользователь успешно добавлен.");
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(User user, Integer userId) throws ValidationException {
        getUserById(userId);
        user.setId(userId);
        User userForUpdate = userMapper.toEntity(getUserById(user.getId()));
        if (user.getEmail() != null &&
                !user.getEmail().equals(userForUpdate.getEmail())) {
            String emailForUpdate = user.getEmail();
            checkUniqueEmail(emailForUpdate);
            userForUpdate.setEmail(user.getEmail());
        }

        if (user.getName() != null &&
                !user.getName().equals(userForUpdate.getName())) {
            userForUpdate.setName(user.getName());
        }

        userStorage.updateUser(userForUpdate, userId);
        log.debug("Информация о пользователе успешно обновлена.");
        return getUserById(userId);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userStorage.getAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id) {
        List<UserDto> users = getAllUsers();
        Optional<UserDto> foundUser = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if (foundUser.isEmpty()) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }
        return foundUser.get();
    }

    @Override
    public void deleteUserById(int id) {
        getUserById(id);
        userStorage.deleteUserById(id);
    }

    @Override
    public void deleteAllUsers() {
        userStorage.deleteAllUsers();
    }

    private void checkUniqueEmail(String email) throws ValidationException {
        List<User> users = userStorage.getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                throw new ValidationException("Пользователь с такой почтой уже существует.");
            }
        }
    }
}

