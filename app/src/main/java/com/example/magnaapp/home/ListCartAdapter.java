package com.example.magnaapp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magnaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adapter utilizzato per visualizzare nel carrello i cibi selezionati dal menu che si vogliono acquistare
 */

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_row, parent, false);
        return new MyViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //TODO da modificare inserendo i dati ricevuti dal db
        holder.textOrdinato.setText(list[position]);
        holder.textPrezzoCart.setText("10");
        holder.textQuantity.setText("2");

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    //rimossa classe statica
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textOrdinato, textPrezzoCart, textQuantity;
        FloatingActionButton fabRemoveItem;



        public MyViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            textOrdinato = itemView.findViewById(R.id.textOrdinato);
            textPrezzoCart = itemView.findViewById(R.id.textPrezzoCart);
            textQuantity = itemView.findViewById(R.id.textQuantity);

            fabRemoveItem = itemView.findViewById(R.id.fabRemoveItem);


            fabRemoveItem.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAbsoluteAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                    //TODO rimuove la row dalla recyclerView
                    ListCartAdapter.this.removeItem(getAbsoluteAdapterPosition());

                }
            });
        }
    }

    public void removeItem(int position) {
        List<String> list = new ArrayList<String>(Arrays.asList(this.list));
        list.remove(position);
        this.list = list.toArray(new String[0]);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(int position) {

    }
}
