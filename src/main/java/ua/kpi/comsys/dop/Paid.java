package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/**
 * Замовлення оплачено.
 *
 * @param paymentId ідентифікатор платежу, не {@code null} і не порожній
 * @param amount    сума платежу, не {@code null} і більша за нуль
 * @author Sviatoslav Opatsa
 */
public record Paid(String paymentId, BigDecimal amount) implements OrderStatus {

    /**
     * Перевіряє коректність полів.
     *
     * @throws IllegalArgumentException якщо значення полів некоректні
     */
    public Paid {
        if (paymentId == null || paymentId.isBlank()) {
            throw new IllegalArgumentException("Payment ID cannot be null or blank");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}