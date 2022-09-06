package com.example.magnaapp.home.section;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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
import java.util.List;
import java.util.Map;

public class ListSectionAdapter extends RecyclerView.Adapter<ListSectionAdapter.MyViewHolder> implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;
    String[] list;
    private static Map<String, Integer> selectedFood;

    public ListSectionAdapter(RecyclerViewInterface recyclerViewInterface, String[] list) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.list = list;
        selectedFood = new HashMap<String, Integer>();
    }

    @NonNull
    @Override
    public ListSectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_row, parent, false);
        return new MyViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSectionAdapter.MyViewHolder holder, int position) {
        holder.text.setText(list[position]);
        holder.number.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                // This will be the text from the EditText
                Integer value = Integer.parseInt(s.toString().trim());
                String key = holder.number.getText().toString().trim();

                selectedFood.put(list[holder.getBindingAdapterPosition()],Integer.parseInt(key));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    @Override
    public int getItemCount() {
        return list.length;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        FloatingActionButton fabAdd, fabRemove;
        EditText number;

        public MyViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            fabAdd = itemView.findViewById(R.id.fabAdd);
            fabRemove = itemView.findViewById(R.id.fabRemove);
            number = itemView.findViewById(R.id.number);

            fabAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int num = Integer.parseInt(number.getText().toString().trim());
                    num++;
                    number.setText(String.valueOf(num));
                }
            });

            fabRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int num = Integer.parseInt(number.getText().toString().trim());
                    if (num > 0) num--;
                    number.setText(String.valueOf(num));
                }
            });
        }
    }

    public static Map<String, Integer> getData(){
        return selectedFood;
    }


    @Override
    public void onItemClick(int position) {

    }
}

