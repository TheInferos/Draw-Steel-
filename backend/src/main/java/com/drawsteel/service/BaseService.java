package com.drawsteel.service;

import com.drawsteel.model.BaseModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<T extends BaseModel> {
    
    List<T> getAll();
    
    Optional<T> getById(UUID id);
    
    T create(T entity);
    
    T update(UUID id, T entityDetails);
    
    void delete(UUID id);
    
    Optional<T> getByName(String name);
}
