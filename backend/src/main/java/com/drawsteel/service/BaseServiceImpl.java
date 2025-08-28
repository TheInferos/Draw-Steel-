package com.drawsteel.service;

import com.drawsteel.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseServiceImpl<T extends BaseModel, R extends JpaRepository<T, UUID>> implements BaseService<T> {
    
    protected final R repository;
    
    protected BaseServiceImpl(R repository) {
        this.repository = repository;
    }
    
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
    
    @Override
    public Optional<T> getById(UUID id) {
        return repository.findById(id);
    }
    
    @Override
    @Transactional
    public T create(T entity) {
        // Check if entity with the same name already exists
        if (entity.getName() != null && getByName(entity.getName()).isPresent()) {
            throw new IllegalArgumentException("Entity with name '" + entity.getName() + "' already exists");
        }
        
        // Check if entity with the same ID already exists (if ID is provided)
        if (entity.getId() != null && repository.findById(entity.getId()).isPresent()) {
            throw new IllegalArgumentException("Entity with ID '" + entity.getId() + "' already exists");
        }
        
        // JPA will handle ID generation automatically via @PrePersist if needed
        return repository.save(entity);
    }
    

    
    @Override
    @Transactional
    public T update(UUID id, T entityDetails) {
        Optional<T> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            T existingEntity = optionalEntity.get();
            
            // Check if the new name conflicts with another entity (excluding the current one)
            if (entityDetails.getName() != null) {
                Optional<T> existingWithName = getByName(entityDetails.getName());
                if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                    throw new IllegalArgumentException("Entity with name '" + entityDetails.getName() + "' already exists");
                }
            }
            
            // Update fields using reflection to handle all fields dynamically
            updateEntityFields(existingEntity, entityDetails);
            
            return repository.save(existingEntity);
        }
        throw new RuntimeException("Entity not found with id: " + id);
    }
    
    @Override
    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
    
    @Override
    public Optional<T> getByName(String name) {
        // This method should be implemented by subclasses if they have name-based queries
        // For now, we'll search through all entities (not efficient for large datasets)
        return repository.findAll().stream()
                .filter(entity -> name.equals(entity.getName()))
                .findFirst();
    }
    
    /**
     * Updates entity fields using reflection to handle all fields dynamically.
     * This allows subclasses to override specific field update logic if needed.
     */
    protected void updateEntityFields(T existingEntity, T entityDetails) {
        try {
            Class<?> clazz = existingEntity.getClass();
            while (clazz != null && clazz != BaseModel.class) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object newValue = field.get(entityDetails);
                    if (newValue != null) {
                        field.set(existingEntity, newValue);
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to update entity fields", e);
        }
    }
}
