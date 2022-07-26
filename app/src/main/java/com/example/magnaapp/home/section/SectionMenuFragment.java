package com.example.magnaapp.home.section;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magnaapp.R;
import com.example.magnaapp.home.database.Data;
import com.example.magnaapp.home.RecyclerViewInterface;
import com.example.magnaapp.home.database.FabToDb;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Fragment che si apre al click di una row della RecyclerView del menu e contiene la recyclerView
 * con tutti i piatti da aggiungere al carrello
 */
public class SectionMenuFragment extends Fragment implements View.OnClickListener, RecyclerViewInterface{
    RecyclerView recyclerView;
    ExtendedFloatingActionButton fabOrdina;
    FloatingActionButton fabClose;
    String[] arrayPortata;
    int[] arrayPrezzo;
    private final int position;

    public SectionMenuFragment(int contentLayoutId) {
        super(contentLayoutId);
        position = contentLayoutId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_section_menu, container, false);

        TypedArray portata = getResources().obtainTypedArray(R.array.portate);
        arrayPortata = getResources().getStringArray(portata.getResourceId(position,0));

        TypedArray prezzo = getResources().obtainTypedArray(R.array.prezziPortate);
        arrayPrezzo = getResources().getIntArray(prezzo.getResourceId(position,0));

        fabOrdina = view.findViewById(R.id.fabOrdina);
        fabClose = view.findViewById(R.id.fabClose);

        fabOrdina.setOnClickListener(this);
        fabClose.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerViewMenuSection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ListSectionAdapter((RecyclerViewInterface) this, arrayPortata, arrayPrezzo));

        prezzo.recycle();
        portata.recycle();
        return view;
    }

    /**
     * Al click del button per aggiungere al cartello si ottiene la mappa contenente tutti i cibi
     * con la quantità selezionata dall'utente e si tolgono quelli con quantità pari a zero.
     * Per finire si chiama la classe per mandare i dati sul database di firebase
     */
    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.fabClose:
                System.out.println("chiuditi");
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            default:
                //Map<String, ArrayList<Integer>> foodQuantity = ListSectionAdapter.getData();
                ArrayList<Data> foodQuantity = ListSectionAdapter.getData();
                /*
                for (int i = 0; i < foodQuantity.size(); i++) {
                    foodQuantity.values().remove(0);

                }
                 */
                foodQuantity.removeIf( name -> name.getQuantity()==0);

                if(!foodQuantity.isEmpty()){
                    //new FabToDb(foodQuantity,"OldOrders");
                    new FabToDb(foodQuantity);
                }
        }
    }

    @Override
    public void onItemClick(int position) {

    }

}
