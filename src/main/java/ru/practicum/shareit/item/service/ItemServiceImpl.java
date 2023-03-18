package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ResourceException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapping.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
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
        item.setOwner(userService.getUserById(idOwner));
        itemStorage.add(item);
        return itemStorage.getById(item.getId());
    }

    @Override
    public ItemDto update(Item item, int idOwner, String itemId) {
        item.setOwner(userService.getUserById(idOwner));

        int idItem = Integer.parseInt(itemId);
        Item itemForUpdate = itemMapper.toEntity(getById(idItem));

        if (idOwner != itemForUpdate.getOwner().getId()) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "Неправильный id владельца вещи");
        }

        if (item.getAvailable() != null) {
            itemForUpdate.setAvailable(item.getAvailable());
        }
        if (item.getName() != null) {
            itemForUpdate.setName(item.getName());
        }
        if (item.getDescription() != null) {
            itemForUpdate.setDescription(item.getDescription());
        }

        itemStorage.update(itemForUpdate, idOwner);
        return itemStorage.getById(idItem);
    }

    @Override
    public ItemDto getById(Integer itemId) {
        return itemStorage.getById(itemId);
    }

    @Override
    public List<ItemDto> getAllItemsByOwner(int idOwner) {
        return itemStorage.getAll().stream()
                .filter(item -> item.getOwner().getId().equals(idOwner))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        String textInLowerCase = text.toLowerCase();
        List<ItemDto> allItems = itemStorage.getAll().stream()
                .filter(ItemDto::isAvailable)
                .toList();
        List<ItemDto> itemsFound = new ArrayList<>();
        if (text.isEmpty()) {
            return itemsFound;
        }

        for (ItemDto item : allItems) {
            if (item.getName().toLowerCase().contains(textInLowerCase)
                    || item.getDescription().toLowerCase().contains(textInLowerCase)) {
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }
}
