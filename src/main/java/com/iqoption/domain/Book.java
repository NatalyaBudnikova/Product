package com.iqoption.domain;

public class Book extends Product {
    private String author;

    public Book() {
    }

    public Book(int id, String name, int price, String author) {
        super(id, name, price);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean matches(Product item, String text) {
        boolean result = super.matches(item, text);
        if (!result) {
        Book book = (Book) item;
        result = book.getAuthor().equalsIgnoreCase(text);
        }
        return result;
    }
}
