package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.Valid;
import java.util.List;

@Component
public interface UserStorage {

    UserDto addUser(@Valid UserDto user);

    UserDto updateUser(UserDto user, Integer id);

    List<UserDto> getAllUsers();

    UserDto getUserById(int id);

    void deleteUserById(int id);

    void deleteAllUsers();

}
