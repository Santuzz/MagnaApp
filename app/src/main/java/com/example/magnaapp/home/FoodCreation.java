package com.example.magnaapp.home;

public class FoodCreation {

    public String montanare, crostiniMisti, tagliereSalumi, insalataDiGamberi, couppoNapoletano;

    public FoodCreation() {

    }

    public FoodCreation(String one, String two, String three, String four, String five, String tipo) {

        if (tipo.contentEquals("Antipasti")) {
            this.montanare = one;
            this.crostiniMisti = two;
            this.tagliereSalumi = three;
            this.insalataDiGamberi = four;
            this.couppoNapoletano = five;
        }
    }
}
