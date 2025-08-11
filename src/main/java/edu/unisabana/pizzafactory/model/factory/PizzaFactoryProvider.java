package edu.unisabana.pizzafactory.model.factory;

/**
 * Proveedor de fábricas concretas. Aísla la selección de variante para que el
 * cliente (PreparadorPizza) no conozca clases concretas.
 */
public final class PizzaFactoryProvider {
    private PizzaFactoryProvider() {}

    /** Devuelve la fábrica concreta según el tipo de masa. */
    public static PizzaFactory get(TipoMasa tipo) {
        switch (tipo) {
            case DELGADA:  return new PizzaDelgadaFactory();
            case GRUESA:   return new PizzaGruesaFactory();
            case INTEGRAL: return new PizzaIntegralFactory();
            default: throw new IllegalArgumentException("Tipo no soportado: " + tipo);
        }
    }

    /** Formateo de entrada para evitar errores de imputacion del cliente. */
    public static PizzaFactory get(String tipo) {
        return get(TipoMasa.valueOf(tipo.trim().toUpperCase()));
    }
}
