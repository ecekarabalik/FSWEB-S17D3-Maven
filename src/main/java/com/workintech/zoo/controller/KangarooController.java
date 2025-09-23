package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/kangaroos") // Tam yol: /workintech/kangaroos
public class KangarooController {

    // görev: map adı "kangaroos"
    private final Map<Integer, Kangaroo> kangaroos = new ConcurrentHashMap<>();

    public KangarooController() {
        kangaroos.put(1, new Kangaroo(1, "Rex", 1.6, 55.0, "MALE", true));
        kangaroos.put(2, new Kangaroo(2, "Ruby", 1.4, 48.0, "FEMALE", false));
    }

    @GetMapping
    public List<Kangaroo> getAll() { return new ArrayList<>(kangaroos.values()); }

    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable Integer id) {
        Kangaroo k = kangaroos.get(id);
        if (k == null) throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        return k;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kangaroo create(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() == null)
            throw new ZooException("Id is required", HttpStatus.BAD_REQUEST);
        if (kangaroos.containsKey(kangaroo.getId()))
            throw new ZooException("Kangaroo already exists with id: " + kangaroo.getId(), HttpStatus.CONFLICT);
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable Integer id, @RequestBody Kangaroo incoming) {
        if (!kangaroos.containsKey(id))
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        incoming.setId(id);
        kangaroos.put(id, incoming);
        return incoming;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Kangaroo removed = kangaroos.remove(id);
        if (removed == null)
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    // görev metnindeki yazım hatası için alias:
    @DeleteMapping("/developers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlias(@PathVariable Integer id) { delete(id); }
}