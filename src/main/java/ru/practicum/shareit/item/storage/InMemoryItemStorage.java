package ru.practicum.shareit.item.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.IdCreator;
import ru.practicum.shareit.exception.ResourceException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapping.ItemMapper;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InMemoryItemStorage implements ItemStorage {

    private final IdCreator idCreator = new IdCreator();
    private final HashMap<Integer, Item> items = new HashMap<>();
    private final ItemMapper itemMapper;

    @Override
    public ItemDto add(Item item, int idOwner) {
        item.setId(idCreator.createId());
        items.put(item.getId(), item);
        return itemMapper.toDto(item);
    }

    @Override
    public ItemDto update(Item item, int idOwner) {
        items.put(item.getId(), item);
        return itemMapper.toDto(item);
    }

    @Override
    public ItemDto getById(Integer itemId) {
        Optional<Item> itemEntity = items.keySet()
                .stream()
                .filter(currentItemId -> Objects.equals(currentItemId, itemId))
                .map(items::get)
                .findFirst();
        if (itemEntity.isEmpty()) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "Вещь с id = " + itemId + " не найдена.");
        } else {
            return itemMapper.toDto(itemEntity.get());
        }
    }

    @Override
    public List<ItemDto> getAll() {
        return items.values()
                .stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }
}
