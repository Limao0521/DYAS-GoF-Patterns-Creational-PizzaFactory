package edu.unisabana.pizzafactory.model.gruesa;

import edu.unisabana.pizzafactory.model.ExcepcionParametrosInvalidos;
import edu.unisabana.pizzafactory.model.Tamano;
import edu.unisabana.pizzafactory.model.api.Moldeador;

/** Moldeado específico para masa gruesa. */
public class MoldeadorPizzaGruesa implements Moldeador {

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
                throw new ExcepcionParametrosInvalidos("Tamaño no soportado para GRUESA: " + tam);
        }
    }

    public void moldearPizzaPequena() {
        System.out.println("[GRUESA] Moldeando pizza PEQUEÑA (gruesa)...");
    }

    public void moldearPizzaMediana() {
        System.out.println("[GRUESA] Moldeando pizza MEDIANA (gruesa)...");
    }
}
