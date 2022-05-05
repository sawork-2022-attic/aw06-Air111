package com.example.webpos.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Cart implements Serializable {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        Product product = item.getProduct();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().equals(product)) {
                int amount = items.get(i).getQuantity();
                amount += item.getQuantity();
                if (amount < 0)
                    return false;
                if (amount == 0)
                    items.remove(i);
                else
                    items.get(i).addQuantity(item.getQuantity());
                return true;
            }
        }
        return items.add(item);
    }

    public boolean removeProduct(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId().equals(id)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        return total;
    }

}
