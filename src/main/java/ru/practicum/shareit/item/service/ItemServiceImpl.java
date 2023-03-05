package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ResourceException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapping.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemStorage itemStorage;
    private final ItemMapper itemMapper;
    private final UserService userService;

    @Override
    public ItemDto add(Item item, int idOwner) {
        item.setOwner(getOwner(idOwner));
        return itemStorage.add(item);
    }

    @Override
    public ItemDto update(Item item, int idOwner, String itemId) {
        int idItem = Integer.parseInt(itemId);
        Item itemForUpdate = itemMapper.toEntity(getById(idItem));

        if (item.getAvailable() != itemForUpdate.getAvailable()) {
            itemForUpdate.setAvailable(item.getAvailable());
        }
        return itemStorage.update(itemForUpdate, idOwner);
    }

    @Override
    public ItemDto getById(Integer itemId) {
        return itemStorage.getById(itemId);
    }

    @Override
    public List<ItemDto> getAllItemsByOwner(int idOwner) {
        UserDto user = getOwner(idOwner);
        return user.getItems().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        List<ItemDto> allItems = itemStorage.getAll();
        return allItems.stream()
                .filter(itemDto -> itemDto.getName().equals(text)
                        || itemDto.getDescription().equals(text))
                .filter(ItemDto::isAvailable)
                .collect(Collectors.toList());
    }

    private void checkItem(int id) {
        ItemDto item = itemStorage.getAll().get(id);
        if (item == null) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "Вещь с id = " + id + " не найдена.");
        } else if (id < 0) {
            throw new ResourceException(HttpStatus.BAD_REQUEST, "Отрицательные значения не допустимы.");
        }
    }

    private UserDto getOwner(int idOwner) {
        return userService.getUserById(idOwner);
    }
}
