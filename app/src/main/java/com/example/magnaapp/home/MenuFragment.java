package com.example.magnaapp.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magnaapp.R;
import com.example.magnaapp.home.section.ListSectionAdapter;
import com.example.magnaapp.home.section.SectionMenuFragment;


public class MenuFragment extends Fragment implements RecyclerViewInterface {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ListAdapter((RecyclerViewInterface) this, getResources().getStringArray(R.array.menuList)));
        return view;
    }

    /**
     * Al click su una riga della recyclerView il MenuFragment viene sostituito da un nuovo
     * SectionMenuFragment in cui si possono selezionare i cibi da aggiungere al carrello
     * @param position indica la posizione della riga della recyclerView premuta
     */
    @Override
    public void onItemClick(int position) {

        SectionMenuFragment nextFrag = new SectionMenuFragment(position);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(this.getId(), nextFrag)
                .addToBackStack(null)
                .commit();
    }
}