package edu.unisabana.pizzafactory.model.factory;

import edu.unisabana.pizzafactory.model.api.Amasador;
import edu.unisabana.pizzafactory.model.api.Moldeador;
import edu.unisabana.pizzafactory.model.api.Horneador;
import edu.unisabana.pizzafactory.model.integral.AmasadorPizzaIntegral;
import edu.unisabana.pizzafactory.model.integral.MoldeadorPizzaIntegral;
import edu.unisabana.pizzafactory.model.integral.HorneadorPizzaIntegral;

/** Fábrica concreta: crea los pasos específicos de la variante INTEGRAL. */
public class PizzaIntegralFactory implements PizzaFactory {
    @Override public Amasador crearAmasador()   { return new AmasadorPizzaIntegral(); }
    @Override public Moldeador crearMoldeador() { return new MoldeadorPizzaIntegral(); }
    @Override public Horneador crearHorneador() { return new HorneadorPizzaIntegral(); }
}
