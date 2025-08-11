package edu.unisabana.pizzafactory.model.delgada;

import edu.unisabana.pizzafactory.model.ExcepcionParametrosInvalidos;
import edu.unisabana.pizzafactory.model.Tamano;
import edu.unisabana.pizzafactory.model.api.Moldeador;

/** Moldeado específico para masa delgada. */
public class MoldeadorPizzaDelgada implements Moldeador {

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
                throw new ExcepcionParametrosInvalidos("Tamaño no soportado para DELGADA: " + tam);
        }
    }

    public void moldearPizzaPequena() {
        System.out.println("[DELGADA] Moldeando pizza PEQUEÑA (delgada)...");
    }

    public void moldearPizzaMediana() {
        System.out.println("[DELGADA] Moldeando pizza MEDIANA (delgada)...");
    }
}
