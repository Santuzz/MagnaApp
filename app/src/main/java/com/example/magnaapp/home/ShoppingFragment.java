package com.example.magnaapp.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magnaapp.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class ShoppingFragment extends Fragment implements View.OnClickListener, RecyclerViewInterface {

    RecyclerView recyclerView;
    ExtendedFloatingActionButton fabConfermaOrdine;
    String[] array;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_shopping, container, false);

        //TODO Leggo i dati dal db con le voci da aggiungere al menu e le inserisco nell'array
        array = getResources().getStringArray(R.array.antipasti);

        fabConfermaOrdine= view.findViewById(R.id.fabConfermaOrdine);

        recyclerView = view.findViewById(R.id.recyclerViewStorage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //TODO passare al posto dell'array il pacchetto di dati ricevuto dal db
        recyclerView.setAdapter(new ListCartAdapter((RecyclerViewInterface) this, array));

        return view;
    }

    @Override
    public void onClick(View view) {
        //TODO invia tutti i piatti ordinati al db e verrà aggiunto allo storico degli ordini (pastOrderFragment)
    }

    @Override
    public void onItemClick(int position) {

    }
}