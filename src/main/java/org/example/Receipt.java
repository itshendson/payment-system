package org.example;

/**
 * Receipt represents a successful purchase by the customer. Upon customer's successful purchase,
 * we store the purchased product and the change owed to the customer in Receipt.
 * @param <T1> Product
 * @param <T2> List of change owed to customer
 */
public class Receipt <T1, T2> {
    private T1 product;
    private T2 tender;

    public Receipt(T1 product, T2 tender) {
        this.product = product;
        this.tender = tender;
    }

    public T1 getProduct() {
        return product;
    }

    public T2 getTender() {
        return tender;
    }
}
