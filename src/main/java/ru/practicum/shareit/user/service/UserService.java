package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
public interface UserService {

    UserDto addUser(User user);

    UserDto updateUser(User user, Integer userId);

    List<UserDto> getAllUsers();

    UserDto getUserById(int id);

    void deleteUserById(int id);

    void deleteAllUsers();
}
