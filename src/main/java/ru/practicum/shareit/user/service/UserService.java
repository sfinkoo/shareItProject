package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.Valid;
import java.util.List;

@Service
public interface UserService {

    UserDto addUser(@Valid UserDto user) throws ValidationException;

    UserDto updateUser(UserDto user, Integer userId) throws ValidationException;

    List<UserDto> getAllUsers();

    UserDto getUserById(int id);

    void deleteUserById(int id);

    void deleteAllUsers();
}
