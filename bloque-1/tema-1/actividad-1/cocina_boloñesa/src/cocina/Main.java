package cocina;

import cocina.modelo.Cocinero;
import cocina.modelo.Container;
import cocina.modelo.Ingredient;
import cocina.receta.Receipt;
import cocina.receta.ReceiptStep;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cocinero cocinero = new Cocinero("Luigi");

        Container olla = new Container("Olla de agua");
        Container sarten = new Container("Sartén grande");
        Container colador = new Container("Colador");
        Container plato = new Container("Plato");

        Ingredient agua = new Ingredient("Agua", 2000, "ml");
        Ingredient sal = new Ingredient("Sal", 10, "g");
        Ingredient espaguetis = new Ingredient("Espaguetis", 400, "g");
        Ingredient cebolla = new Ingredient("Cebolla", 1, "unidades");
        Ingredient ajo = new Ingredient("Ajo", 2, "unidades");
        Ingredient aceite = new Ingredient("Aceite de oliva", 30, "ml");
        Ingredient carne = new Ingredient("Carne picada", 300, "g");
        Ingredient tomate = new Ingredient("Tomate triturado", 400, "g");

        Receipt receta = new Receipt("Espaguetis a la boloñesa");
        receta.anadirPaso(new ReceiptStep("Poner agua a hervir", olla, List.of(agua, sal), 3));
        receta.anadirPaso(new ReceiptStep("Cocer los espaguetis", olla, List.of(espaguetis), 6));
        receta.anadirPaso(new ReceiptStep("Sofreír la cebolla y el ajo", sarten, List.of(cebolla, ajo, aceite), 4));
        receta.anadirPaso(new ReceiptStep("Añadir la carne picada y dorarla", sarten, List.of(carne), 5));
        receta.anadirPaso(new ReceiptStep("Añadir el tomate y reducir la salsa", sarten, List.of(tomate), 7));
        receta.anadirPaso(new ReceiptStep("Escurrir la pasta", colador, List.of(espaguetis), 2));
        receta.anadirPaso(new ReceiptStep("Mezclar la pasta con la salsa boloñesa", sarten, List.of(espaguetis), 3));
        receta.anadirPaso(new ReceiptStep("Emplatar", plato, List.of(espaguetis, carne, tomate), 1));

        receta.ejecutar(cocinero);
    }
}
