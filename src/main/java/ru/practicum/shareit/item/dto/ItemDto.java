package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.AbstractDto;

/**
 * TODO Sprint add-controllers.
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto extends AbstractDto {

    private String name;

    private String description;

    private boolean available;

    private Integer numberOfUses;
}
