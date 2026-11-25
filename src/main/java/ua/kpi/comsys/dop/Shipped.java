package ua.kpi.comsys.dop;

import java.time.LocalDate;

/**
 * Замовлення відправлено.
 *
 * @param trackingCode код відстеження, не {@code null} і не порожній
 * @param dispatchDate дата відправлення, не {@code null}
 * @author Sviatoslav Opatsa
 */
public record Shipped(String trackingCode, LocalDate dispatchDate) implements OrderStatus {

    /**
     * Перевіряє коректність полів.
     *
     * @throws IllegalArgumentException якщо значення полів некоректні
     */
    public Shipped {
        if (trackingCode == null || trackingCode.isBlank()) {
            throw new IllegalArgumentException("Tracking code cannot be null or blank");
        }
        if (dispatchDate == null) {
            throw new IllegalArgumentException("Dispatch date cannot be null");
        }
    }
}