package ua.kpi.comsys.dop;

/**
 * Замовлення скасовано.
 *
 * @param reason причина скасування, не {@code null} і не порожня
 * @author Sviatoslav Opatsa
 */
public record Cancelled(String reason) implements OrderStatus {

    /**
     * Перевіряє коректність причини.
     *
     * @throws IllegalArgumentException якщо причина некоректна
     */
    public Cancelled {
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Reason cannot be null or blank");
        }
    }
}