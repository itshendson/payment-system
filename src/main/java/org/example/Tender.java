package org.example;

/**
 * Tender represents bills and coins that customers can use to pay for purchases.
 * Bills and coins are represented in cents (eg. $5 = 100 cents)
 */
public enum Tender {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    LOONIE(100),
    TOONIE(200),
    FIVE(500),
    TEN(1000),
    TWENTY(2000),
    FIFTY(5000),
    HUNDRED(10000);

    private int value;

    Tender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
