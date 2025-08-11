package edu.unisabana.pizzafactory.model.integral;

import edu.unisabana.pizzafactory.model.api.Horneador;

/** Horneado específico para masa integral. */
public class HorneadorPizzaIntegral implements Horneador {
    @Override
    public void hornear() {
        // Ajustes de horneado para integral (puede requerir tiempos/temperaturas distintos)
        System.out.println("[INTEGRAL] Horneando pizza integral...");
    }
}
