package com.iqoption.repository;

import com.iqoption.domain.Product;

public class ProductRepository {
    private Product[] items = new Product[0];

    public void save(Product item) {
        Product[] tmp = new Product[items.length + 1];

        System.arraycopy(items, 0, tmp, 0, items.length);

        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;

        items = tmp;
    }

    public Product findByID(int id) {
        Product findItem = null;
        for (Product item : items) {
            if (item.getId() == id) {
                findItem = item;
                break;
            }
        }
        return findItem;
    }

    public void removeById(int id) {
        if (items.length <= 0) {
            return;
        }

        if (findByID(id) == null) {
            return;
        }

        Product[] tmp = new Product[items.length - 1];
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }


    public void removeAll() {
        items = new Product[0];
    }

    public Product[] getAll() {
        return items;
    }


}
