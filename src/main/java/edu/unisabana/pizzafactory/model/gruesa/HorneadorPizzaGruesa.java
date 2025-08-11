package edu.unisabana.pizzafactory.model.gruesa;

import edu.unisabana.pizzafactory.model.api.Horneador;

/** Horneado específico para masa gruesa. */
public class HorneadorPizzaGruesa implements Horneador {
    @Override
    public void hornear() {
        // Logica real para hornear pizza gruesa
        System.out.println("[GRUESA] Horneando pizza gruesa...");
    }
}
