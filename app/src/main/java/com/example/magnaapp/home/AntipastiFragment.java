package com.example.magnaapp.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.Toast;

import com.example.magnaapp.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;


public class AntipastiFragment extends Fragment implements View.OnClickListener{

    private ExtendedFloatingActionButton fabOrdina;
    private FloatingActionButton fabAdd1;
    private EditText numberOne,numberTwo,numberThree,numberFour,numberFive;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {

        // Inflate the layout for this fragment

        View view = (View) inflater.inflate(R.layout.fragment_antipasti, container, false);

        System.out.println("prova");

        fabOrdina = view.findViewById(R.id.fabOrdina);
        fabAdd1 = view.findViewById(R.id.fabAdd1);

        fabAdd1.setOnClickListener(this);
        fabOrdina.setOnClickListener(this);

        numberOne = view.findViewById(R.id.numberOne);
        numberThree = view.findViewById(R.id.numberThree);
        numberFour = view.findViewById(R.id.numberFour);
        numberTwo = view.findViewById(R.id.numberTwo);
        numberFive = view.findViewById(R.id.numberFive);

        return view;

    }


    public void onClick(View view) {

        String numberOne=this.numberOne.getText().toString().trim();
        String numberTwo=this.numberTwo.getText().toString().trim();
        String numberThree=this.numberThree.getText().toString().trim();
        String numberFour=this.numberFour.getText().toString().trim();
        String numberFive=this.numberFive.getText().toString().trim();
        System.out.println("prova");

        switch(view.getId()){
            case R.id.fabAdd1:
                int num1 = Integer.parseInt(numberOne);
                num1++;
                System.out.println("prova");
                this.numberOne.setText(String.valueOf(num1));
                break;
            case R.id.fabOrdina:
                new FloatingActionButtonToDb(numberOne,numberTwo,numberThree,numberFour,numberFive,"antipasto");

}


        
    }


}
