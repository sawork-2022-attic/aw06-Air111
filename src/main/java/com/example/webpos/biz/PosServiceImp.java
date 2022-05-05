package com.example.webpos.biz;

import com.example.webpos.model.ProductEntity;
import com.example.webpos.db.PosDB;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PosServiceImp implements PosService, Serializable {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }


    @Override
    public Product randomProduct() {
        return products().get(ThreadLocalRandom.current().nextInt(0, products().size()));
    }

    @Override
    public void checkout(Cart cart) {

    }

    @Override
    public Cart add(Cart cart, Product product, int amount) {
        return add(cart, product.getId(), amount);
    }

    @Override
    public Cart add(Cart cart, String productId, int amount) {

        Optional<ProductEntity> optionalProduct = posDB.findById(productId);
        if (optionalProduct.isEmpty()) return cart;

        ProductEntity entity = optionalProduct.get();

        Product product = new Product(entity.getAsin(),
                entity.getTitle(), entity.getPrice(), entity.getImageURLHighRes());
        cart.addItem(new Item(product, amount));
        return cart;
    }

    @Override
    public Cart remove(Cart cart, String productId) {
        cart.removeProduct(productId);
        return cart;
    }

    @Override
    public List<Product> products() {
        List<Product> res = new ArrayList<>();
        int i = 0;
        final int limit = 20;
        for (ProductEntity entity: posDB.findAll()) {
            res.add(new Product(entity.getAsin(),
                    entity.getTitle(), entity.getPrice(), entity.getImageURLHighRes()));
            i = i + 1;
            if (i >= limit)
                break;
        }
        return res;
    }
}
