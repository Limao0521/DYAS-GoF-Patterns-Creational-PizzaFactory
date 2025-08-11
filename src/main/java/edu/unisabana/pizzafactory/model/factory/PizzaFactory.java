package edu.unisabana.pizzafactory.model.factory;

import edu.unisabana.pizzafactory.model.api.Amasador;
import edu.unisabana.pizzafactory.model.api.Moldeador;
import edu.unisabana.pizzafactory.model.api.Horneador;

/**
 * Fábrica abstracta: declara los métodos de creación para cada paso del proceso.
 * Cada variante (delgada, gruesa, integral) tendrá su implementación concreta.
 */
public interface PizzaFactory {
    Amasador crearAmasador();
    Moldeador crearMoldeador();
    Horneador crearHorneador();
}
