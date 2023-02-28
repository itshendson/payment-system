package org.example;

import java.util.List;

/**
 * PaymentSystem interface defines the contract for PaymentSystemImpl.
 * PaymentSystem should allow customers to select product to order, take payment from customer, reset the overall
 * system, allow customer to stop and refund payment, and if purchase is successful, to return a receipt containing
 * the product and change owed to the customer.
 */
public interface PaymentSystem {

    public void selectProduct(Product product);
    public void receivePayment(Tender tender);
    public void reset();
    public List<Tender> refund();
    public Receipt<Product, List<Tender>> buyProduct();

}
