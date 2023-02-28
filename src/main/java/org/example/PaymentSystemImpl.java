package org.example;

import org.example.exception.InsufficientPaymentException;
import org.example.exception.InsufficientTenderException;
import org.example.exception.SoldOutException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Purpose of PaymentSystemImpl is to perform transaction of products and tender with the customer. This class is
 * responsible for interfacing with the productInventory and cashInventory on behalf of the customer.
 */
public class PaymentSystemImpl implements PaymentSystem {
    private Inventory<Product> productInventory = new Inventory<>();
    private Inventory<Tender> tenderInventory = new Inventory<>();
    private Product selectedProduct;
    private int paymentInProcess;

    public PaymentSystemImpl() {
        loadTenderAndProduct();
    }

    /**
     * Initialize PaymentSystem with products and tender to use.
     */
    private void loadTenderAndProduct() {
        for (Product p : Product.values()) {
            productInventory.put(p, 5);
        }

        for (Tender t : Tender.values()) {
            tenderInventory.put(t, 5);
        }
    }

    /**
     * Customer selects product to order.
     */
    @Override
    public void selectProduct(Product product) {
        if (productInventory.has(product)) {
            selectedProduct = product;
        } else {
            throw new SoldOutException("Product is not available to purchase.");
        }
    }

    /**
     * Handles when customer pays money.
     * Method should put money into tenderInventory.
     */
    @Override
    public void receivePayment(Tender tender) {
        tenderInventory.add(tender);
        paymentInProcess += tender.getValue();
    }

    /**
     * Resets the state of tenderInventory, productInventory, selectedProduct, paymentInProcess and re-initialize
     * the inventory with the default amount.
     */
    @Override
    public void reset() {
        tenderInventory.clear();
        productInventory.clear();
        selectedProduct = null;
        paymentInProcess = 0;
        loadTenderAndProduct();
    }

    /**
     * Stop customer's current transaction and refund to them a list of tender equal to the amount of tender they
     * had put in. Method also resets selectedProduct and paymentInProcess.
     * @return list of change owed to customer
     */
    @Override
    public List<Tender> refund() {
        // calculate change for customers
        List<Tender> change = calculateAndGetChange(paymentInProcess);

        // reset values
        selectedProduct = null;
        paymentInProcess = 0;

        // return list of change to customer
        return change;
    }

    /**
     * Helper method that uses greedy algorithm for the coin change problem. Program creates a list
     * of Tender starting with the highest tender value to the lowest called tenderSortedByDesc. The
     * purpose of this list is to be consumed by the greedy algorithm.
     *
     * Next, for each of the Tender denomination (eg. Hundred, Fifty, etc.), we perform two checks:
     * 1. Is the amount of money greater than the current denomination looked at
     * 2. Is there enough tender in tenderInventory to return to the customer
     * If both conditions are true, the program takes the tender from tenderInventory and add it to
     * change list.
     *
     * We then continue to iterate through each of the denomination repeating the same check.
     *
     * After iterating through tenderSortedByDesc, the program checks to see if balance is still greater than 0.
     * If so, it means we have checked all the Tender and still do not have enough Tender to pay back customer.
     *
     * Return change.
     * @param amount the amount of money we need to return as change to customer
     * @return list of tender owed to customer
     */
    private List<Tender> calculateAndGetChange(int amount) {
        List<Tender> change = new ArrayList<>();

        List<Tender> tenderSortedByDesc = Arrays.asList(Tender.HUNDRED, Tender.FIFTY, Tender.TWENTY, Tender.TEN, Tender.FIVE,
                Tender.TOONIE, Tender.LOONIE, Tender.QUARTER, Tender.DIME, Tender.NICKEL, Tender.PENNY);

        int balance = amount;
        for (Tender t : tenderSortedByDesc) {
            while (balance >= t.getValue() && tenderInventory.has(t)) {
                tenderInventory.deduct(t);
                change.add(t);
                balance -= t.getValue();
            }
        }

        if (balance > 0) throw new InsufficientTenderException("Cash registry does not have enough change to pay back customer");

        return change;
    }

    /**
     * Method represents situation where customer decides to purchase the product.
     * If customer decides to buy product, program has to:
     * 1. Check if customer has paid enough for price of product
     * 2. Check that tenderInventory has enough change to give customer
     * 3. If both check passes, assign the selectedProduct to purchasedProduct variable for use later
     * 4. Calculate change that is owed to customer
     * 5. Reset selectedProduct and paymentInProcess
     * 6. Return a Receipt representing product and list of change
     * @return
     */
    @Override
    public Receipt<Product, List<Tender>> buyProduct() {
        // Check customer's payment is greater than price of product
        if (paymentInProcess < selectedProduct.getPrice()) throw new InsufficientPaymentException("Customer has not paid enough tender.");

        // Check if tenderInventory has enough change for customer
        try {
            calculateAndGetChange(paymentInProcess - selectedProduct.getPrice());
        } catch (InsufficientTenderException e) {
            throw new InsufficientTenderException("Insufficient change to pay customer.");
        }

        // if so, assign selectedProduct to purchasedProduct
        Product purchasedProduct = selectedProduct;

        // Get Change for difference between how much customer paid and how much product cost
        List<Tender> change = calculateAndGetChange(paymentInProcess - selectedProduct.getPrice());

        // Reset selectedProduct and paymentInProcess
        selectedProduct = null;
        paymentInProcess = 0;

        // Return Receipt containing purchasedProduct and change
        return new Receipt<Product, List<Tender>>(purchasedProduct, change);
    }

    /**
     * Method is used for testing only in PaymentSystemImplTest. It allows us to arbitrarily set the
     * amount of money that is currently in paymentInProcess.
     * @param paymentInProcess
     */
    public void setPaymentInProcess(int paymentInProcess) {
        this.paymentInProcess = paymentInProcess;
    }
}
