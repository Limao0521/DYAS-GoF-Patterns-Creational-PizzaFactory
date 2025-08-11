package edu.unisabana.pizzafactory.model.factory;

import edu.unisabana.pizzafactory.model.api.Amasador;
import edu.unisabana.pizzafactory.model.api.Moldeador;
import edu.unisabana.pizzafactory.model.api.Horneador;
import edu.unisabana.pizzafactory.model.delgada.AmasadorPizzaDelgada;
import edu.unisabana.pizzafactory.model.delgada.MoldeadorPizzaDelgada;
import edu.unisabana.pizzafactory.model.delgada.HorneadorPizzaDelgada;

/** Fábrica concreta: crea los pasos específicos de la variante DELGADA. */
public class PizzaDelgadaFactory implements PizzaFactory {
    @Override public Amasador crearAmasador()   { return new AmasadorPizzaDelgada(); }
    @Override public Moldeador crearMoldeador() { return new MoldeadorPizzaDelgada(); }
    @Override public Horneador crearHorneador() { return new HorneadorPizzaDelgada(); }
}
