package edu.unisabana.pizzafactory.model.delgada;

import edu.unisabana.pizzafactory.model.api.Amasador;

/** Amasado específico para masa delgada. */
public class AmasadorPizzaDelgada implements Amasador {
    @Override
    public void amasar() {
        // Lógica real de amasado para masa delgada 
        System.out.println("[DELGADA] Amasando masa delgada...");
    }
}
