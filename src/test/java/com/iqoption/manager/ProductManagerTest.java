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
    Book book = new Book(1, "The Witcher", 12, "Sapkowski");
    Smartphone smartphone = new Smartphone(2, "iphone8", 134, "apple");
    Book book1 = new Book(3, "Onegin", 12, "Pushkin");
    Smartphone smartphone1 = new Smartphone(4, "galaxy", 134, "android");

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
    void searchByNothing() {
        Product[] expected = {};
        assertArrayEquals(expected, manager.searchBy("book1"));
    }

    @Test
    void searchByBook() {
        Product[] expected = {book};
        assertArrayEquals(expected, manager.searchBy("the witcher"));
    }

    @Test
    void searchBySmartphone() {
        Product[] expected = {smartphone};
        assertArrayEquals(expected, manager.searchBy("iphone8"));
    }

}