package ru.practicum.shareit.item.model;

import lombok.Data;
import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class Item {

    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @Size(max = 200)
    private String description;

    private UserDto owner;

    private boolean isFree;

    @Positive
    private Integer numberOfUses;
}
