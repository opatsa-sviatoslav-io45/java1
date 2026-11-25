package ua.kpi.comsys.dop;

/**
 * Статус замовлення. Запечатана ієрархія незмінних даних: {@link Pending},
 * {@link Paid}, {@link Shipped} та {@link Cancelled}.
 *
 * @author Sviatoslav Opatsa
 */
public sealed interface OrderStatus permits Pending, Paid, Shipped, Cancelled {
}