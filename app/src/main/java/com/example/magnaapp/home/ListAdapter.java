package com.example.magnaapp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magnaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * adapter utilizzato dalla recyclerView del MenuFragment
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;
    String[] list;

    public ListAdapter(RecyclerViewInterface recyclerViewInterface, String[] list) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list[position]);

    }

    @Override
    public int getItemCount() {
        return list.length;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAbsoluteAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}

