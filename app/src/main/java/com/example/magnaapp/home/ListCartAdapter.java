package com.example.magnaapp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magnaapp.R;
import com.example.magnaapp.home.database.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.magnaapp.home.database.DbToCart;

import java.util.ArrayList;

/**
 * Adapter utilizzato per visualizzare nel carrello i cibi selezionati dal menu che si vogliono acquistare
 */

public class ListCartAdapter extends RecyclerView.Adapter<ListCartAdapter.MyViewHolder> implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<Data> dataReceived;

    public ListCartAdapter(RecyclerViewInterface recyclerViewInterface, ArrayList<Data> dataReceived) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.dataReceived = dataReceived;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_row, parent, false);
        return new MyViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println(dataReceived.get(position).getPlate());
        holder.textOrdinato.setText(dataReceived.get(position).getPlate());
        holder.textPrezzoCart.setText(String.valueOf(dataReceived.get(position).getPrice()));
        holder.textQuantity.setText(String.valueOf(dataReceived.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return dataReceived.size();
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

                    ListCartAdapter.this.removeItem(getAbsoluteAdapterPosition());
                }
            });
        }
    }

    public void removeItem(int position) {
        dataReceived.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(int position) {

    }
}
