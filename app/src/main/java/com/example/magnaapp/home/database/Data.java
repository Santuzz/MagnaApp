package com.example.magnaapp.home.database;

import androidx.annotation.NonNull;

/**
 * classe utilizzata per formattare i dati inviati al db in modo da poterli leggere correttamente
 * Ad ogni piatto è associata una quantità e il prezzo
 */
public class Data {
    private String plate;
    private int quantity;
    private int price;

    public Data(){

    }
    public Data(String plate, int quantity, int price){
        this.plate = plate;
        this.quantity = quantity;
        this.price = price;
    }

    public Data(Data value) {
        this.plate = value.plate;
        this.quantity = value.quantity;
        this.price = value.price;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @NonNull
    public String toString(){
        return "{"+ "Plate: " + this.getPlate() + ", "+ "Quantity: " + this.getQuantity() + ", "+ "Price: " + this.getPrice() + "}";
    }
}
