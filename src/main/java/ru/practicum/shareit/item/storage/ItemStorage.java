package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;

import javax.validation.Valid;
import java.util.List;

@Component
public interface ItemStorage {

    ItemDto add(@Valid ItemDto item);

    ItemDto update(ItemDto item, int idOwner);

    ItemDto getById(Integer itemId);

    List<ItemDto> getAll();

}
