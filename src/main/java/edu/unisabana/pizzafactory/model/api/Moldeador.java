package edu.unisabana.pizzafactory.model.api;

import edu.unisabana.pizzafactory.model.ExcepcionParametrosInvalidos;
import edu.unisabana.pizzafactory.model.Tamano;

/**
 * Paso 2 del proceso: moldear la pizza según el tamaño.
 * Cada variante valida qué tamaños soporta y lanza excepción si no aplica.
 */
public interface Moldeador {
    void moldear(Tamano tam) throws ExcepcionParametrosInvalidos;
}
