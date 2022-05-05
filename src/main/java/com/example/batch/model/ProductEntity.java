package com.example.batch.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class ProductEntity {
    private String main_cat;

    private String title;

    @Id
    private String asin;

    private double price;

    private String category;

    private String imageURLHighRes;
}
