package com.example.magnaapp.home;

/**
 * interfaccia da usare nelle recyclerView per aggiungere il metodo onItemClick che viene utilizzato
 * per far accadere qualcosa al click di una voce della recyclerView portandosi dietro la posizione
 * della row premuta
 */

public interface RecyclerViewInterface {
    void onItemClick(int position);
}
