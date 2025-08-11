package edu.unisabana.pizzafactory.consoleview;

//Imports para excepciones ingrediente y tamano
import edu.unisabana.pizzafactory.model.ExcepcionParametrosInvalidos;
import edu.unisabana.pizzafactory.model.Ingrediente;
import edu.unisabana.pizzafactory.model.Tamano;

//Imports para metodos del proceso de cada pizza
import edu.unisabana.pizzafactory.model.api.Amasador;
import edu.unisabana.pizzafactory.model.api.Moldeador;
import edu.unisabana.pizzafactory.model.api.Horneador;

//Imports para factorys por tipo
import edu.unisabana.pizzafactory.model.factory.PizzaFactory;
import edu.unisabana.pizzafactory.model.factory.PizzaFactoryProvider;
import edu.unisabana.pizzafactory.model.factory.TipoMasa;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreparadorPizza {

    private static final Logger LOG = Logger.getLogger(PreparadorPizza.class.getName());

    private final PizzaFactory factory;

    public PreparadorPizza(PizzaFactory factory) {
        this.factory = factory;
    }

    public static void main(String[] args) {
        try {
            // Selección de variante por argumento (arg0) o propiedad del sistema -Dtipo
            String tipoStr = (args != null && args.length > 0)
                    ? args[0]
                    : System.getProperty("tipo", "DELGADA");

            TipoMasa tipo = TipoMasa.valueOf(tipoStr.trim().toUpperCase());
            PizzaFactory factory = PizzaFactoryProvider.get(tipo);

            PreparadorPizza pp = new PreparadorPizza(factory);

            Ingrediente[] ingredientes = new Ingrediente[]{
                    new Ingrediente("queso", 1),
                    new Ingrediente("jamon", 2)
            };

            pp.prepararPizza(ingredientes, Tamano.MEDIANO); //Preparar pizza con tamaño MEDIANO e ingredientes como: queso, jamón CONSTANTES unicamente varia tipo de pizza

        } catch (IllegalArgumentException e) {
            LOG.log(Level.SEVERE, "Tipo de masa inválido. Use: DELGADA, GRUESA o INTEGRAL.", e);
        } catch (ExcepcionParametrosInvalidos ex) {
            LOG.log(Level.SEVERE, "Problema en la preparación de la pizza", ex);
        }
    }

    public void prepararPizza(Ingrediente[] ingredientes, Tamano tam)
            throws ExcepcionParametrosInvalidos {

        Amasador amasador   = factory.crearAmasador();
        Moldeador moldeador = factory.crearMoldeador();
        Horneador horneador = factory.crearHorneador();

        // Secuencia inmutable
        amasador.amasar();
        moldeador.moldear(tam);                 // la validación de tamaño vive en cada familia
        aplicarIngredientes(ingredientes);
        horneador.hornear();
    }

    private void aplicarIngredientes(Ingrediente[] ingredientes) {
        LOG.log(Level.INFO, "APLICANDO INGREDIENTES!: {0}", Arrays.toString(ingredientes));
        // CODIGO DE LLAMADO AL MICROCONTROLADOR
    }
}
