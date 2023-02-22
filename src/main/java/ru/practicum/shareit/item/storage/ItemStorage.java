package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Component
public interface ItemStorage {

    ItemDto add(Item item, int idOwner);

    ItemDto update(Item item, int idOwner);

    ItemDto getById(Integer itemId);

    List<ItemDto> getAll();

}
