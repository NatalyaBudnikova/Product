package com.iqoption.manager;

import com.iqoption.domain.Book;
import com.iqoption.domain.Product;
import com.iqoption.domain.Smartphone;
import com.iqoption.exception.NotFoundException;
import com.iqoption.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductManager manager = new ProductManager(new ProductRepository());
    Book book = new Book(1, "The Witcher", 12, "Sapkowski");
    Smartphone smartphone = new Smartphone(2, "iphone8", 134, "apple");
    Book book1 = new Book(3, "Onegin", 12, "Pushkin");
    Smartphone smartphone1 = new Smartphone(4, "iphone5", 134, "apple");

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
        assertThrows(NotFoundException.class, () -> manager.removeById(16));
    }

    @Test
    void searchByNothing() {
        Product[] expected = {};
        assertArrayEquals(expected, manager.searchBy("book1"));
    }

    @Test
    void searchByAuthorBook() {
        Product[] expected = {book};
        assertArrayEquals(expected, manager.searchBy("Sapkowski"));
    }

    @Test
    void searchByNameBook() {
        Product[] expected = {book};
        assertArrayEquals(expected, manager.searchBy("The Witcher"));
    }

    @Test
    void searchByNameSmartphone() {
        Product[] expected = {smartphone};
        assertArrayEquals(expected, manager.searchBy("iphone8"));
    }

    @Test
    void searchByManufacturerSmartphone() {
        manager.add(smartphone1);
        Product[] expected = {smartphone, smartphone1};
        assertArrayEquals(expected, manager.searchBy("apple"));
    }

}