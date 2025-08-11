package edu.unisabana.pizzafactory.model.factory;

import edu.unisabana.pizzafactory.model.api.Amasador;
import edu.unisabana.pizzafactory.model.api.Moldeador;
import edu.unisabana.pizzafactory.model.api.Horneador;
import edu.unisabana.pizzafactory.model.gruesa.AmasadorPizzaGruesa;
import edu.unisabana.pizzafactory.model.gruesa.MoldeadorPizzaGruesa;
import edu.unisabana.pizzafactory.model.gruesa.HorneadorPizzaGruesa;

/** Fábrica concreta: crea los pasos específicos de la variante GRUESA. */
public class PizzaGruesaFactory implements PizzaFactory {
    @Override public Amasador crearAmasador()   { return new AmasadorPizzaGruesa(); }
    @Override public Moldeador crearMoldeador() { return new MoldeadorPizzaGruesa(); }
    @Override public Horneador crearHorneador() { return new HorneadorPizzaGruesa(); }
}
