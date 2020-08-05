package com.iqoption.domain;

public class Smartphone extends Product {
    private String manufacturer;

    public Smartphone(int id, String name, int price, String manufacturer) {
        super(id, name, price);
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Smartphone() {
    }

    @Override
    public boolean matches(Product item, String text) {
        boolean result = super.matches(item, text);
        if (!result) {
            Smartphone smartphone = (Smartphone) item;
            result = smartphone.getManufacturer().equalsIgnoreCase(text);
        }
        return result;
    }
}

