package edu.unisabana.pizzafactory.model.integral;

import edu.unisabana.pizzafactory.model.ExcepcionParametrosInvalidos;
import edu.unisabana.pizzafactory.model.Tamano;
import edu.unisabana.pizzafactory.model.api.Moldeador;

/** Moldeado específico para masa integral. */
public class MoldeadorPizzaIntegral implements Moldeador {

    @Override
    public void moldear(Tamano tam) throws ExcepcionParametrosInvalidos {
        switch (tam) {
            case PEQUENO:
                moldearPizzaPequena();
                break;
            case MEDIANO:
                moldearPizzaMediana();
                break;
            default:
                throw new ExcepcionParametrosInvalidos("Tamaño no soportado para INTEGRAL: " + tam);
        }
    }

    public void moldearPizzaPequena() {
        System.out.println("[INTEGRAL] Moldeando pizza PEQUEÑA (integral)...");
    }

    public void moldearPizzaMediana() {
        System.out.println("[INTEGRAL] Moldeando pizza MEDIANA (integral)...");
    }
}
