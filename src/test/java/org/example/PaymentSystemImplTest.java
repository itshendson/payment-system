package org.example;

import org.example.exception.InsufficientPaymentException;
import org.example.exception.InsufficientTenderException;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentSystemImplTest {
    PaymentSystemImpl ps;

    @BeforeEach
    void setUp() {
        ps = new PaymentSystemImpl();
    }

    @AfterEach
    void tearDown() {
        ps = null;
    }

    @Test
    void refund() {
        ps.selectProduct(Product.PRODUCT_B);
        ps.receivePayment(Tender.HUNDRED);
        int expected = Tender.HUNDRED.getValue();
        int actual = sumTenderList(ps.refund());
        assertEquals(expected, actual);
    }

    @Test
    void buyProductOverPaid() {
        ps.selectProduct(Product.PRODUCT_B);
        ps.receivePayment(Tender.FIFTY);
        ps.receivePayment(Tender.TWENTY);
        int expected = Tender.FIFTY.getValue() + Tender.TWENTY.getValue() - Product.PRODUCT_B.getPrice();
        int actual = sumTenderList(ps.buyProduct().getTender());
        assertEquals(expected, actual);
    }

    @Test
    void buyProductInsufficientTender() {
        ps.selectProduct(Product.PRODUCT_C);
        ps.setPaymentInProcess(100000);
        assertThrows(InsufficientTenderException.class, () -> ps.buyProduct());
    }

    @Test
    void buyProductInsufficientPayment() {
        ps.selectProduct(Product.PRODUCT_C);
        ps.receivePayment(Tender.QUARTER);
        assertThrows(InsufficientPaymentException.class, () -> ps.buyProduct());
    }

    @Ignore
    private int sumTenderList(List<Tender> tenderList) {
        int sum = 0;
        for (Tender t : tenderList) {
            sum += t.getValue();
        }
        return sum;
    }
}