package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private final Map<Integer, Koala> koalas = new ConcurrentHashMap<>();

    @GetMapping
    public List<Koala> getAll() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable Integer id) {
        Koala k = koalas.get(id);
        if (k == null) throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        return k;
    }

    @PostMapping
    public Koala create(@RequestBody Koala koala) {
        if (koala.getId() == null)
            throw new ZooException("Id is required", HttpStatus.BAD_REQUEST);
        koalas.put(koala.getId(), koala); // upsert
        return koala; // 200
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable Integer id, @RequestBody Koala incoming) {
        if (!koalas.containsKey(id))
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        incoming.setId(id);
        koalas.put(id, incoming);
        return incoming; // 200
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable Integer id) {
        Koala removed = koalas.remove(id);
        if (removed == null)
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        return removed; // 200
    }
}
