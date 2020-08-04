package com.iqoption.repository;

import com.iqoption.domain.Book;
import com.iqoption.domain.Product;
import com.iqoption.domain.Smartphone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    Book book = new Book(1, "book1", 12, "author1");
    Smartphone smartphone = new Smartphone(2, "phone1", 134, "apple");

    @BeforeEach
    void setUp() {
        repository.save(book);
        repository.save(smartphone);
    }

    @Test
    void removeById() {
        repository.removeById(2);
        assertNull(repository.findByID(2));
    }

    @Test
    void removeByIdWithoutItems() {
        ProductRepository repository = new ProductRepository();
        repository.removeById(2);

        Product[] expected = {};
        assertArrayEquals(expected, repository.getAll());
    }

    @Test
    void removeAll() {
        repository.removeAll();
        Product[] expected = {};
        assertArrayEquals(expected, repository.getAll());
    }

    @Test
    void findById() {
        assertEquals(smartphone, repository.findByID(2));
    }

    @Test
    void findByIdNotFind() {
        assertNull(repository.findByID(5));
    }

}