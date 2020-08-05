package com.iqoption.manager;

import com.iqoption.domain.Book;
import com.iqoption.domain.Product;
import com.iqoption.domain.Smartphone;
import com.iqoption.repository.ProductRepository;

public class ProductManager {
    static final int DEFAULT_QUANTITY = 2;
    private ProductRepository repository;
    private int quantity;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
        this.quantity = DEFAULT_QUANTITY;
    }
    public ProductManager(ProductRepository repository, int quantity) {
        this.repository = repository;
        this.quantity = quantity;
    }

    public void add(Product item) {
       repository.save(item);
    }

    public Product[] getAll() {
        Product[] items = repository.getAll();
        Product[] tmp = new Product[items.length];

        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = items[items.length - i - 1];

        }

        return tmp;

    }
    public  void removeById(int id) {
        repository.removeById(id);
    }

    public Product findById(int id) {
        return repository.findByID(id);
    }

    public Product[] findAll() {
        Product[] items = repository.getAll();
        quantity = Math.min(items.length, quantity);
        Product[] tmp = new Product[quantity];
        int tmpIndex = 0;
        for (int lastIndex = items.length - 1; lastIndex >= 0 && tmpIndex <= quantity-1; lastIndex--) {
            tmp[tmpIndex] = items[lastIndex];
            tmpIndex++;
        }
        return tmp;
    }

    public void removeAll() {
        repository.removeAll();
    }

    public Product[] searchBy(String text) {
        Product[] items = repository.getAll();
        Product[] result = new Product[0];
        for (Product item : items) {
           if (item.matches(text)) {
               Product[] tmp = new Product[result.length + 1];
               System.arraycopy(result, 0, tmp, 0, result.length);
               tmp[tmp.length - 1] = item;
               result = tmp;
           }
        }
        return result;
    }


}
