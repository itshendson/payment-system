package org.example;

import java.util.HashMap;

/**
 * Inventory is a wrapper class around HashMap.
 * Inventory is used by the PaymentSystemImpl to store either Product or Tender
 * @param <T> product or tender
 */
public class Inventory <T> {
    HashMap<T, Integer> inventory = new HashMap();

    /**
     * Put product or tender in to inventory and number of each product or inventory.
     * @param type
     * @param quantity
     */
    public void put(T type, int quantity) {
        inventory.put(type, quantity);
    }

    /**
     * Method is used to see how much quantity of a product or tender there are.
     * @param product is the product the customer would like to buy.
     * @return value of key
     */
    public int getQuantity(T product) {
        return inventory.get(product);
    }

    /**
     * Checks if a product or tender is in inventory.
     * @param product is the product the customer would like to buy.
     * @return true if there are one or more product in inventory.
     */
    public boolean has(T product) {
        return getQuantity(product) > 0;
    }

    /**
     * When customers put money in, the money should be sorted into tenderInventory.
     * @param tender
     */
    public void add(T tender) {
        int count = inventory.get(tender);
        inventory.put(tender, count + 1);
    }

    /**
     * Clears the hashmap.
     */
    public void clear() {
        inventory.clear();
    }

    /**
     * Deduct money from the tenderInventory
     * @param tender
     */
    public void deduct(T tender) {
        int count = inventory.get(tender);
        inventory.put(tender, count - 1);
    }
}
