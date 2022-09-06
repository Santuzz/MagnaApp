package com.example.magnaapp.home;

//TODO classe da eliminare
public class FoodCreation {

    public String crostiniMisti, tagliereSalumi, insalataDiGamberi, lumachine,montanare;

    public FoodCreation() {

    }

    public FoodCreation(String one, String two, String three, String four, String five, String tipo) {

        if (tipo.contentEquals("Antipasti")) {
            this.montanare = five;
            this.crostiniMisti = one;
            this.tagliereSalumi = two;
            this.insalataDiGamberi = three;
            this.lumachine = four;
        }

    }
}
