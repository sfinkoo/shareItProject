package ru.practicum.shareit.item.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Objects;

@Component
public class ItemMapper {

    private final ModelMapper mapper;

    public ItemMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Item toEntity(ItemDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Item.class);
    }

    public ItemDto toDto(Item entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ItemDto.class);
    }
}
