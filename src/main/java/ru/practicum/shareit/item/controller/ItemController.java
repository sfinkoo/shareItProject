package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final HttpServletRequest request;
    private final UserService userService;


    @PostMapping
    public ItemDto add(@Valid @RequestBody Item item, HttpServletRequest request) {
        return itemService.add(item, getIdOwner(request));
    }

    @PatchMapping
    public ItemDto update(@Valid @RequestBody Item item, HttpServletRequest request) {
        return itemService.update(item, getIdOwner(request));
    }

    @GetMapping("/{itemId}")
    public ItemDto getById(@PathVariable Integer itemId) {
        return itemService.getById(itemId);
    }

    @GetMapping
    public List<ItemDto> getAllItemsByOwner(HttpServletRequest request) {
        return itemService.getAllItemsByOwner(getIdOwner(request));
    }

    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String text) {
        return itemService.searchItems(text);
    }

    private int getIdOwner(HttpServletRequest request) {
        return Integer.parseInt(request.getHeader("X-Sharer-User-Id"));
    }
}
