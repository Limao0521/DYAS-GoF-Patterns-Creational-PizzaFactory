package edu.unisabana.pizzafactory.model.integral;

import edu.unisabana.pizzafactory.model.api.Amasador;

/** Amasado específico para masa integral. */
public class AmasadorPizzaIntegral implements Amasador {
    @Override
    public void amasar() {
        // Ajustes para harinas integrales (absorción de agua, reposo, etc.)
        System.out.println("[INTEGRAL] Amasando masa integral...");
    }
}
