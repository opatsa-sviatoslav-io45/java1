package ua.kpi.comsys.dop;

/**
 * Формує текстовий опис статусу замовлення.
 *
 * <p>Обробка вичерпна: {@link OrderStatus} запечатаний, тому компілятор
 * перевіряє, що всі варіанти враховані, і {@code default} не потрібен.
 *
 * @author Sviatoslav Opatsa
 */
/*
 *  TODO: зробити клас абстрактним, а метод - статичним
 */
public class OrderProcessor {

    /**
     * Перетворює статус замовлення на багаторядковий опис за шаблоном.
     *
     * @param status статус замовлення, не може бути {@code null}
     * @return текст опису, що закінчується переводом рядка
     * @throws IllegalArgumentException якщо {@code status} дорівнює {@code null}
     */
    public String process(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return switch (status) {
            case Pending p -> """
                    Order Status: PENDING
                    Details: Order is currently pending processing.
                    """;
            case Paid p -> """
                    Order Status: PAID
                    Details: Payment ID: %s, Amount: $%s
                    """.formatted(p.paymentId(), p.amount());
            case Shipped s -> """
                    Order Status: SHIPPED
                    Details: Tracking Code: %s, Dispatch Date: %s
                    """.formatted(s.trackingCode(), s.dispatchDate());
            case Cancelled c -> """
                    Order Status: CANCELLED
                    Details: Reason: %s
                    """.formatted(c.reason());
        };
    }
}