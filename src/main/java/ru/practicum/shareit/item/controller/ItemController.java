package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final String idOwnerInHeader = "X-Sharer-User-Id";
    private final ItemService itemService;


    @PostMapping
    public ItemDto add(@Valid @RequestBody ItemDto item, @RequestHeader(idOwnerInHeader) Integer idOwner) {
        return itemService.add(item, idOwner);
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestBody ItemDto item, @RequestHeader(idOwnerInHeader) Integer idOwner,
                          @PathVariable String itemId) throws ValidationException {
        return itemService.update(item, idOwner, itemId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getById(@PathVariable Integer itemId) {
        return itemService.getById(itemId);
    }

    @GetMapping
    public List<ItemDto> getAllItemsByOwner(@RequestHeader(idOwnerInHeader) Integer idOwner) {
         return itemService.getAllItemsByOwner(idOwner);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String text) {
        return itemService.searchItems(text);
    }
}
