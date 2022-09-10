package com.example.magnaapp.home;

import static com.example.magnaapp.login.LoginActivity.connection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magnaapp.R;
import com.example.magnaapp.home.database.Data;
import com.example.magnaapp.home.database.DbToCart;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShoppingFragment extends Fragment implements View.OnClickListener, RecyclerViewInterface {

    RecyclerView recyclerView;
    ExtendedFloatingActionButton fabConfermaOrdine, fabClean, fabRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_shopping, container, false);

        DbToCart cart = new DbToCart();
        cart.readToDb();

        fabConfermaOrdine = view.findViewById(R.id.fabConfermaOrdine);
        fabClean = view.findViewById(R.id.fabClean);
        fabRefresh = view.findViewById(R.id.fabRefresh);

        recyclerView = view.findViewById(R.id.recyclerViewStorage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ListCartAdapter((RecyclerViewInterface) this, new ArrayList<Data>()));

        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(new ListCartAdapter((RecyclerViewInterface) ShoppingFragment.this, cart.getFinalReceived()));
            }
        });

        fabClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(new ListCartAdapter((RecyclerViewInterface) ShoppingFragment.this, new ArrayList<Data>()));
                cart.deleteToDb();
            }
        });

        fabConfermaOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(new ListCartAdapter((RecyclerViewInterface) ShoppingFragment.this, new ArrayList<Data>()));
                cart.confirmToDb();
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onItemClick(int position) {

    }
}