package com.example.batch.service;

import com.example.batch.model.Product;
import com.example.batch.model.ProductEntity;
import com.example.batch.repository.ProductRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProductWriter implements ItemWriter<Product>, StepExecutionListener {

    private ProductRepository productRepository;

    private Set<String> keys = new HashSet<>();

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public void write(List<? extends Product> list) throws Exception {
//        list.forEach(System.out::println);
//        System.out.println("chunk written");
        for (Product product: list) {
            //Optional<ProductEntity> optionalProductEntity = productRepository.findById(product.getAsin());
            //List<?> entities = productRepository.getProductEntitiesByAsin(product.getAsin());
            //if (optionalProductEntity.isPresent())
            //if (!entities.isEmpty())
//            if (productRepository.existsById(product.getAsin()))
//                continue;
//            else {
//                if (product.getAsin().equals("B00005N7NQ"))
//                    System.out.println("wtf");
//            }
            String key = product.getAsin();
            if (keys.contains(key))
                continue;
            keys.add(key);
            ProductEntity entity = new ProductEntity();
            entity.setMain_cat(product.getMain_cat());
            entity.setAsin(product.getAsin());
            entity.setTitle(product.getTitle());
            List<String> catagory = product.getCategory();
            if (catagory.size() > 0)
                entity.setCategory(catagory.get(catagory.size() - 1));
            else
                entity.setCategory("");
            List<String> imageURLHighRes = product.getImageURLHighRes();
            if (imageURLHighRes.size() > 0)
                entity.setImageURLHighRes(imageURLHighRes.get(0));
            else
                entity.setImageURLHighRes("");
            if (!product.getPrice().equals("")) {
                try {
                    entity.setPrice(Double.parseDouble(product.getPrice()));
                }
                catch (NumberFormatException e) {
                    entity.setPrice(0.0);
                }
            }
            else
                entity.setPrice(0.0);
            productRepository.save(entity);
            //productRepository.flush();
        }
    }
}
