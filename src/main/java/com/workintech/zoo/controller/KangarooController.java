package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    // Boş başlat: testler POST'la ekleyecek
    private final Map<Integer, Kangaroo> kangaroos = new ConcurrentHashMap<>();

    @GetMapping
    public List<Kangaroo> getAll() {
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable Integer id) {
        Kangaroo k = kangaroos.get(id);
        if (k == null) throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        return k;
    }

    // Test 200 OK bekliyor; id çakışırsa da upsert yap
    @PostMapping
    public Kangaroo create(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() == null)
            throw new ZooException("Id is required", HttpStatus.BAD_REQUEST);
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo; // 200
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable Integer id, @RequestBody Kangaroo incoming) {
        if (!kangaroos.containsKey(id))
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        incoming.setId(id);
        kangaroos.put(id, incoming);
        return incoming; // 200
    }

    // Test 200 bekliyor; 204 verme
    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable Integer id) {
        Kangaroo removed = kangaroos.remove(id);
        if (removed == null)
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        return removed; // 200 + body
    }

    // (görev metnindeki typo için istersen tut)
    @DeleteMapping("/developers/{id}")
    public Kangaroo deleteAlias(@PathVariable Integer id) { return delete(id); }
}