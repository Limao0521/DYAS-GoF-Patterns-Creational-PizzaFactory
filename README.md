
---

# DYAS – GoF Patterns (Creational) – Pizza Factory

Este proyecto implementa el patrón **Fábrica Abstracta (Abstract Factory)** para desacoplar la **secuencia fija** de preparación de pizzas de las **variantes de máquina/masa** (delgada, gruesa, integral). El objetivo es permitir agregar nuevas variantes **sin tocar** la lógica principal.

## 🎯 Objetivo del cambio

* Mantener **inmutable** la secuencia: `amasar → moldear(según tamaño) → aplicar ingredientes → hornear`.
* Soportar **múltiples variantes** de máquina: *Delgada* (existente), *Gruesa* y *Integral*.
* **Eliminar `new` y `if/else`** de la lógica principal para reducir acoplamiento.
* Preparar el proyecto para **futuras variantes** sin modificar el orquestador (OCP, DIP).

---

## 🧠 Problema original

`PreparadorPizza` hacía esto:

* Instanciaba clases **concretas** de la variante *delgada* (`new AmasadorPizzaDelgada()`, etc.).
* Decidía **tamaños** con `if/else` (`PEQUENA`, `MEDIANA`) y lanzaba la excepción allí mismo.
* Para nuevas variantes (gruesa/integral) habría que **tocar** el flujo y llenar de ramificaciones.

Esto **rompe** principios SOLID (DIP/OCP) y dificulta la evolución.

---

## ✅ Solución aplicada (Abstract Factory)

### 1) **Interfaces de productos (API)**

Se crearon interfaces para representar los pasos del proceso (una *familia* de productos por variante):

* `Amasador` → `void amasar()`
* `Moldeador` → `void moldear(Tamano tam)` (ahora **la validación de tamaño vive aquí**)
* `Horneador` → `void hornear()`

> **Por qué:** El cliente solo conoce **interfaces**. Cada variante provee su implementación concreta.

### 2) **Fábrica Abstracta y Fábricas Concretas**

* `PizzaFactory` (abstracta): crea `Amasador`, `Moldeador`, `Horneador`.
* `PizzaDelgadaFactory`, `PizzaGruesaFactory`, `PizzaIntegralFactory`: devuelven las implementaciones adecuadas por variante.
* `TipoMasa` (enum) y `PizzaFactoryProvider` (selector centralizado de fábrica).

> **Por qué:** Garantiza **coherencia por familia** y centraliza la elección de variante, evitando `new` en el cliente.

### 3) **Refactor de `PreparadorPizza`**

* Ahora **recibe** una `PizzaFactory` y orquesta la **misma secuencia** sin `if/else`:

  1. `factory.crearAmasador().amasar()`
  2. `factory.crearMoldeador().moldear(tam)` ← valida tamaños por **familia**
  3. `aplicarIngredientes(ingredientes)`
  4. `factory.crearHorneador().hornear()`

> **Por qué:** El orquestador queda **estable** y extensible. Nueva variante → nueva fábrica + clases concretas, **sin tocar** el orquestador.

---

## 🗂️ Estructura de paquetes (nueva)

```
src/main/java/edu/unisabana/pizzafactory/
├─ consoleview/
│  └─ PreparadorPizza.java            ← orquestador (cliente)
└─ model/
   ├─ ExcepcionParametrosInvalidos.java
   ├─ Ingrediente.java
   ├─ Tamano.java
   ├─ api/                            ← interfaces (productos abstractos)
   │  ├─ Amasador.java
   │  ├─ Moldeador.java
   │  └─ Horneador.java
   ├─ factory/                        ← fábricas
   │  ├─ PizzaFactory.java
   │  ├─ PizzaFactoryProvider.java
   │  ├─ TipoMasa.java
   │  ├─ PizzaDelgadaFactory.java
   │  ├─ PizzaGruesaFactory.java
   │  └─ PizzaIntegralFactory.java
   ├─ delgada/                        ← implementaciones concretas (existente, movida)
   │  ├─ AmasadorPizzaDelgada.java
   │  ├─ MoldeadorPizzaDelgada.java
   │  └─ HorneadorPizzaDelgada.java
   ├─ gruesa/                         ← implementaciones nuevas
   │  ├─ AmasadorPizzaGruesa.java
   │  ├─ MoldeadorPizzaGruesa.java
   │  └─ HorneadorPizzaGruesa.java
   └─ integral/                       ← implementaciones nuevas
      ├─ AmasadorPizzaIntegral.java
      ├─ MoldeadorPizzaIntegral.java
      └─ HorneadorPizzaIntegral.java
```

---

## 🔁 Qué cambió (resumen)

* **Nuevos archivos**: `api/*`, `factory/*`, `gruesa/*`, `integral/*`.
* **Movidos**: las clases *delgadas* a `model/delgada` y ahora **implementan** `api.*`.
* **`PreparadorPizza`**: dejó de instanciar concretos y de usar `if/else` por tamaño.
* **Validación de tamaños**: se trasladó a cada `Moldeador` **por variante** (lanza `ExcepcionParametrosInvalidos` si no aplica).
* **Selección de variante**: se hace en `main` por **arg o propiedad**, y se resuelve en `PizzaFactoryProvider`.

---

## 🧩 Ejecución

Compilar y ejecutar (por defecto usa `DELGADA`):

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="edu.unisabana.pizzafactory.consoleview.PreparadorPizza"
```

Elegir variante por **argumento**:

```bash
mvn exec:java -Dexec.mainClass="edu.unisabana.pizzafactory.consoleview.PreparadorPizza" -Dexec.args="GRUESA"
```

O por **propiedad del sistema**:

```bash
mvn exec:java -Dexec.mainClass="edu.unisabana.pizzafactory.consoleview.PreparadorPizza" -Dtipo=INTEGRAL
```

---

## 🧪 Ejemplo de uso (flujo)

```java
PizzaFactory factory = PizzaFactoryProvider.get(TipoMasa.INTEGRAL);
PreparadorPizza prep = new PreparadorPizza(factory);
prep.prepararPizza(new Ingrediente[]{ new Ingrediente("queso",1), new Ingrediente("jamon",2) }, Tamano.MEDIANA);
```

* Si el tamaño no es válido para esa familia, el `Moldeador` lanza `ExcepcionParametrosInvalidos`.
* `aplicarIngredientes` queda como punto único para hablar con el **microcontrolador**.

---

## 🧵 Diagrama (PlantUML de la arquitectura)

1) Genera el .puml con Maven

bash
Copiar
Editar
mvn clean process-classes
El archivo se genera en: target/generated-docs/pizza-class-diagram.puml
(Si usas otro nombre/ruta en el plugin, ajusta este paso.)

2) Ábrelo en VS Code y prévisualiza

Instala la extensión PlantUML (autor: jebbs).

Abre pizza-class-diagram.puml.

Ctrl+Shift+P → PlantUML: Preview Current Diagram.

3) Expórtalo a imagen (PNG/SVG)

En la vista previa: Ctrl+Shift+P → PlantUML: Export Current Diagram.

Elige PNG o SVG y guárdalo en docs/diagram.png (o docs/diagram.svg).


![DIAGRAMA UML](src\main\resources\pizza-class-diagram.png)
---

## 📈 Beneficios del refactor

* **DIP/OCP**: el orquestador depende de **abstracciones**, no de concretos. Nueva variante → **cero** cambios en `PreparadorPizza`.
* **Consistencia por familia**: la fábrica concreta garantiza objetos compatibles entre sí.
* **Menos errores**: se elimina el `if/else` de tamaños en el cliente (cada familia valida lo suyo).
* **Testabilidad**: se pueden inyectar fábricas/mocks para probar la secuencia sin hardware.

---

## ➕ Cómo agregar una nueva variante (checklist)

1. Crear paquete `model/<variante>/` y las clases:

   * `AmasadorPizza<Variante>` implementa `Amasador`.
   * `MoldeadorPizza<Variante>` implementa `Moldeador` (valida `Tamano`).
   * `HorneadorPizza<Variante>` implementa `Horneador`.
2. Crear `Pizza<Variante>Factory` que implemente `PizzaFactory`.
3. Añadir la opción en `TipoMasa` y en `PizzaFactoryProvider.get(...)`.
4. Ejecutar y probar (no tocas `PreparadorPizza`).

---

## 🛠️ Notas de tooling (opcional)

* **Diagramas**: puedes generar UML con PlantUML/LivingDoc.
* Si usas PlantUML generator por Maven, configura la ejecución en `process-classes` y asegúrate de **no ocultar métodos/constructores** (`hideMethods=false`, `showConstructors=true`).

---