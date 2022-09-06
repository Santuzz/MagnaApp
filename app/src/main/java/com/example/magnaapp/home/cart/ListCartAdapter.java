package com.example.magnaapp.home.cart;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magnaapp.R;
import com.example.magnaapp.home.RecyclerViewInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class ListCartAdapter extends RecyclerView.Adapter<ListCartAdapter.MyViewHolder> implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;
    String[] list;

    public ListCartAdapter(RecyclerViewInterface recyclerViewInterface, String[] list) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textOrdinato, textPrezzoCart, textQuantity;
        FloatingActionButton fabRemoveItem;


        public MyViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            textOrdinato = itemView.findViewById(R.id.textOrdinato);
            textPrezzoCart = itemView.findViewById(R.id.textPrezzoCart);
            textQuantity = itemView.findViewById(R.id.textQuantity);


            fabRemoveItem.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //TODO rimuove la row dalla recyclerView
                }
            });

        }
    }

    @Override
    public void onItemClick(int position) {

    }
}
