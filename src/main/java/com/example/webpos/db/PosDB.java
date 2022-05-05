package com.example.webpos.db;

import com.example.webpos.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosDB extends CrudRepository<ProductEntity, String> {
}
