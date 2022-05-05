package com.example.batch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.http.entity.StringEntity;

import javax.persistence.*;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String main_cat;

    private String title;

    private String asin;

    private String price;

    private List<String> category;

    private List<String> imageURLHighRes;
}
