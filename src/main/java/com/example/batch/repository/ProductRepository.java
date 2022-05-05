package com.example.batch.repository;

import com.example.batch.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "product_entity")
//    public interface ProductRepository extends JpaRepository<ProductEntity, String> {
public interface ProductRepository extends CrudRepository<ProductEntity, String> {

//    @Query(value = "select asin from product_entity where asin=?1", nativeQuery = true)
//    List<String> getProductEntitiesByAsin(String name);
}
