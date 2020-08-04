package com.iqoption.manager;

import com.iqoption.domain.Book;
import com.iqoption.domain.Product;
import com.iqoption.domain.Smartphone;
import com.iqoption.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductManager manager = new ProductManager(new ProductRepository());
    Book book = new Book(1, "book1", 12, "author1");
    Smartphone smartphone = new Smartphone(2, "phone1", 134, "apple");
    Book book1 = new Book(3, "book2", 12, "author2");

    @BeforeEach
    void setUp() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(book1);
    }

    @Test
    void findDefault() {
        Product[] expected = {book1, smartphone};
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    void findsLessDefault() {
        ProductManager manager = new ProductManager(new ProductRepository());
        manager.add(book);
        Product[] expected = {book};
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    void findNotDefault() {
        ProductManager manager = new ProductManager(new ProductRepository(), 3);
        manager.add(book);
        manager.add(smartphone);
        manager.add(book1);
        Product[] expected = {book1, smartphone, book};
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    void findById() {
        assertEquals(smartphone, manager.findById(2));
    }

    @Test
    void findByIdNull() {
        assertNull(manager.findById(15));
    }

    @Test
    void removeAll() {
        Product[] expected = {};
        manager.removeAll();
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    void removeById() {
        Product[] expected = {book1, book};
        manager.removeById(2);
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    void removeByIdNotFound() {
        Product[] expected = {book1, smartphone};
        manager.removeById(16);
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    void searchBy() {
        Product[] expected = {book};
        assertArrayEquals(expected, manager.searchBy("book1"));
    }

}