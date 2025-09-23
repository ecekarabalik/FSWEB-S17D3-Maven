package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/koalas") // Tam yol: /workintech/koalas
public class KoalaController {

    // görev: map adı "koalas"
    private final Map<Integer, Koala> koalas = new ConcurrentHashMap<>();

    public KoalaController() {
        koalas.put(1, new Koala(1, "Koko", 12.5, 18, "FEMALE"));
        koalas.put(2, new Koala(2, "Milo", 10.1, 20, "MALE"));
    }

    @GetMapping
    public List<Koala> getAll() { return new ArrayList<>(koalas.values()); }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable Integer id) {
        Koala k = koalas.get(id);
        if (k == null) throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        return k;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Koala create(@RequestBody Koala koala) {
        if (koala.getId() == null)
            throw new ZooException("Id is required", HttpStatus.BAD_REQUEST);
        if (koalas.containsKey(koala.getId()))
            throw new ZooException("Koala already exists with id: " + koala.getId(), HttpStatus.CONFLICT);
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable Integer id, @RequestBody Koala incoming) {
        if (!koalas.containsKey(id))
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        incoming.setId(id);
        koalas.put(id, incoming);
        return incoming;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Koala removed = koalas.remove(id);
        if (removed == null)
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
