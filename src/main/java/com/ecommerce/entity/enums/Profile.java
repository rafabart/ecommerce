package com.ecommerce.entity.enums;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CUSTOMER(2, "ROLE_CUSTOMER");

    private int id;
    private String name;

    Profile(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Profile toEnum(final Integer id) {
        if (id == null) {
            return null;
        }

        for (Profile statusPayment : Profile.values()) {
            if (id.equals(statusPayment.getId())) {
                return statusPayment;
            }
        }

        throw new IllegalArgumentException("Perfil inválido: " + id);
    }
}