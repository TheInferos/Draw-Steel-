package com.drawsteel.controller;

import com.drawsteel.model.BaseModel;
import com.drawsteel.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseController<T extends BaseModel> {
    
    protected final BaseService<T> service;
    
    protected BaseController(BaseService<T> service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = service.getAll();
        return ResponseEntity.ok(entities);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable UUID id) {
        Optional<T> entity = service.getById(id);
        return entity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        try {
            T createdEntity = service.create(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable UUID id, @RequestBody T entityDetails) {
        try {
            T updatedEntity = service.update(id, entityDetails);
            return ResponseEntity.ok(updatedEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<T> getByName(@PathVariable String name) {
        Optional<T> entity = service.getByName(name);
        return entity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
