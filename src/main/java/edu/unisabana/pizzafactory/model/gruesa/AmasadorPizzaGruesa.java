package edu.unisabana.pizzafactory.model.gruesa;

import edu.unisabana.pizzafactory.model.api.Amasador;

/** Amasado específico para masa gruesa (tipo pan). */
public class AmasadorPizzaGruesa implements Amasador {
    @Override
    public void amasar() {
        // Logica real para amasado de masa gruesa
        System.out.println("[GRUESA] Amasando masa gruesa...");
    }
}
