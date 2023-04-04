package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.dto.ItemDto;

import javax.validation.Valid;
import java.util.List;

@Service
public interface ItemService {

    ItemDto add(@Valid ItemDto item, int idOwner);

    ItemDto update(ItemDto item, int idOwner, String itemId) throws ValidationException;

    ItemDto getById(Integer itemId);

    List<ItemDto> getAllItemsByOwner(int idOwner);

    List<ItemDto> searchItems(String text);
}
