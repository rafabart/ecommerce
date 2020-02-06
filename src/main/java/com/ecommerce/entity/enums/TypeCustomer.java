package com.ecommerce.entity.enums;

public enum TypeCustomer {

    LEGALPERSON(1, "Pessoa Jurídica"),
    NATURALPERSON(2, "Pessoa Física");

    private int id;
    private String name;


    private TypeCustomer(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static TypeCustomer toEnum(final Integer id) {

        if (id == null) {
            return null;
        }

        for (TypeCustomer typeCustomer : TypeCustomer.values()) {
            if (id.equals(typeCustomer.getId())) {
                return typeCustomer;
            }
        }

        throw new IllegalArgumentException("Tipo de cliente inválido: " + id);
    }
}
