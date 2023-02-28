package org.example;

/**
 * Product represents an enumeration of products that a customer can purchase.
 * Every product will have a name and price. The price will be represented in cent.
 */
public enum Product {
    PRODUCT_A("A", 35),
    PRODUCT_B("B", 5999),
    PRODUCT_C("C", 8562);

    private String name;
    private int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
