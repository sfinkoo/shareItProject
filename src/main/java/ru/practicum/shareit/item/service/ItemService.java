package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Service
public interface ItemService {

    ItemDto add(Item item, int idOwner);

    ItemDto update(Item item, int idOwner);

    ItemDto getById(Integer itemId);

    List<ItemDto> getAllItemsByOwner(int idOwner);

    List<ItemDto> searchItems(String text);
}
