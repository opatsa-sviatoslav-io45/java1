package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;


class OrderProcessorTest {

    private final OrderProcessor processor = new OrderProcessor();

    @Test
    void pending() {
        assertEquals("""
                Order Status: PENDING
                Details: Order is currently pending processing.
                """, processor.process(new Pending()));
    }

    @Test
    void paid() {
        assertEquals("""
                Order Status: PAID
                Details: Payment ID: PAY-1, Amount: $99.50
                """, processor.process(new Paid("PAY-1", new BigDecimal("99.50"))));
    }

    @Test
    void shipped() {
        assertEquals("""
                Order Status: SHIPPED
                Details: Tracking Code: TRK123, Dispatch Date: 2026-11-15
                """, processor.process(new Shipped("TRK123", LocalDate.of(2026, 11, 15))));
    }

    @Test
    void cancelled() {
        assertEquals("""
                Order Status: CANCELLED
                Details: Reason: Out of stock
                """, processor.process(new Cancelled("Out of stock")));
    }

    @Test
    void nullStatus() {
        var ex = assertThrows(IllegalArgumentException.class, () -> processor.process(null));
        assertEquals("Status cannot be null", ex.getMessage());
    }

    @Test
    void paidValidation() {
        BigDecimal ok = BigDecimal.TEN;
        assertThrows(IllegalArgumentException.class, () -> new Paid(null, ok));
        assertThrows(IllegalArgumentException.class, () -> new Paid("  ", ok));
        assertThrows(IllegalArgumentException.class, () -> new Paid("P", null));
        assertThrows(IllegalArgumentException.class, () -> new Paid("P", BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> new Paid("P", new BigDecimal("-1")));
    }

    @Test
    void shippedValidation() {
        LocalDate d = LocalDate.of(2026, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> new Shipped(null, d));
        assertThrows(IllegalArgumentException.class, () -> new Shipped(" ", d));
        assertThrows(IllegalArgumentException.class, () -> new Shipped("T", null));
    }

    @Test
    void cancelledValidation() {
        assertThrows(IllegalArgumentException.class, () -> new Cancelled(null));
        assertThrows(IllegalArgumentException.class, () -> new Cancelled(" "));
    }

    @Test
    void recordsAreValueBased() {
        assertEquals(new Cancelled("x"), new Cancelled("x"));
        assertEquals(new Pending(), new Pending());
    }
}
