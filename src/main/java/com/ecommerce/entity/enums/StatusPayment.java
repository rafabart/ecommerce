package com.ecommerce.entity.enums;

public enum StatusPayment {

    CANCELED(1, "Cancelado"),
    PAID(2, "Pago"),
    PENDING(3, "Pendente");

    private int id;
    private String name;

    StatusPayment(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static StatusPayment toEnum(final Integer id) {
        if (id == null) {
            return null;
        }

        for (StatusPayment statusPayment : StatusPayment.values()) {
            if (id.equals(statusPayment.getId())) {
                return statusPayment;
            }
        }

        throw new IllegalArgumentException("Estado de pagamento inválido: " + id);
    }
}