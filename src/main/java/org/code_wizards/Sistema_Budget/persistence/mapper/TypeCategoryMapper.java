package org.code_wizards.Sistema_Budget.persistence.mapper;

public class TypeCategoryMapper {
    public static boolean isValidType(String tipo) {
        return tipo != null && (tipo.equalsIgnoreCase("Ingreso") || tipo.equalsIgnoreCase("Egreso"));
    }

    public static String normalize(String tipo) {
        if (tipo == null) return null;
        if (tipo.equalsIgnoreCase("Ingreso")) return "Ingreso";
        if (tipo.equalsIgnoreCase("Egreso")) return "Egreso";
        return "Desconocido";
    }
}
