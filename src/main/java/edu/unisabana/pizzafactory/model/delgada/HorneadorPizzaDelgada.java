package edu.unisabana.pizzafactory.model.delgada;

import edu.unisabana.pizzafactory.model.api.Horneador;

/** Horneado específico para masa delgada. */
public class HorneadorPizzaDelgada implements Horneador {
    @Override
    public void hornear() {
        // Lógica real de horneado para masa delgada 
        System.out.println("[DELGADA] Horneando pizza delgada...");
    }
}
