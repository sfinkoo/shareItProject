package ru.practicum.shareit;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractDto implements Serializable {

    private Integer id;
}
