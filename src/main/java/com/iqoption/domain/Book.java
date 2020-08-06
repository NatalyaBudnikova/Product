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
    public boolean matches(String text) {
        if (super.matches(text)) {
            return true;
        }
        return author.equalsIgnoreCase(text);
    }
}
