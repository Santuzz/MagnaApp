package com.example.magnaapp.home;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magnaapp.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;


public class AntipastiFragment extends Fragment implements View.OnClickListener {

    private ExtendedFloatingActionButton fabOrdina;
    private FloatingActionButton fabAdd1, fabAdd2, fabAdd3, fabAdd4, fabAdd5;
    private FloatingActionButton fabRemove1, fabRemove2, fabRemove3, fabRemove4, fabRemove5;
    private EditText numberOne, numberTwo, numberThree, numberFour, numberFive;
    private TextView textOne, textTwo, textThree, textFour, textFive;
    private int position;
    public AntipastiFragment(int contentLayoutId) {
        super(contentLayoutId);
        position = contentLayoutId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {

        // Inflate the layout for this fragment
        //TODO aprire un fragment diverso in base alla voce selezionata (antipasti, primi, secondi,..)
        View view = (View) inflater.inflate(R.layout.fragment_antipasti, container, false);

        fabOrdina = view.findViewById(R.id.fabOrdina);

        fabAdd1 = view.findViewById(R.id.fabAdd1);
        fabAdd2 = view.findViewById(R.id.fabAdd2);
        fabAdd3 = view.findViewById(R.id.fabAdd3);
        fabAdd4 = view.findViewById(R.id.fabAdd4);
        fabAdd5 = view.findViewById(R.id.fabAdd5);

        fabRemove1 = view.findViewById(R.id.fabRemove1);
        fabRemove2 = view.findViewById(R.id.fabRemove2);
        fabRemove3 = view.findViewById(R.id.fabRemove3);
        fabRemove4 = view.findViewById(R.id.fabRemove4);
        fabRemove5 = view.findViewById(R.id.fabRemove5);

        fabAdd1.setOnClickListener(this);
        fabAdd2.setOnClickListener(this);
        fabAdd3.setOnClickListener(this);
        fabAdd4.setOnClickListener(this);
        fabAdd5.setOnClickListener(this);

        fabRemove1.setOnClickListener(this);
        fabRemove2.setOnClickListener(this);
        fabRemove3.setOnClickListener(this);
        fabRemove4.setOnClickListener(this);
        fabRemove5.setOnClickListener(this);

        fabOrdina.setOnClickListener(this);

        numberOne = view.findViewById(R.id.numberOne);
        numberTwo = view.findViewById(R.id.numberTwo);
        numberThree = view.findViewById(R.id.numberThree);
        numberFour = view.findViewById(R.id.numberFour);
        numberFive = view.findViewById(R.id.numberFive);

        textOne = view.findViewById(R.id.textOne);
        textTwo = view.findViewById(R.id.textTwo);
        textThree = view.findViewById(R.id.textThree);
        textFour = view.findViewById(R.id.textFour);
        textFive = view.findViewById(R.id.textFive);

        TypedArray portata = getResources().obtainTypedArray(R.array.portate);
        String[] array = getResources().getStringArray(portata.getResourceId(position,0));

        textOne.setText(array[0]);
        textTwo.setText(array[1]);
        textThree.setText(array[2]);
        textFour.setText(array[3]);
        textFive.setText(array[4]);

        portata.recycle();
        return view;

    }

    public void onClick(View view) {

        String numberOne = this.numberOne.getText().toString().trim();
        String numberTwo = this.numberTwo.getText().toString().trim();
        String numberThree = this.numberThree.getText().toString().trim();
        String numberFour = this.numberFour.getText().toString().trim();
        String numberFive = this.numberFive.getText().toString().trim();
        int num1, num2, num3, num4, num5;

        switch (view.getId()) {
            case R.id.fabAdd1:
                num1 = Integer.parseInt(numberOne);
                num1++;
                this.numberOne.setText(String.valueOf(num1));
                break;
            case R.id.fabAdd2:
                num2 = Integer.parseInt(numberTwo);
                num2++;
                this.numberTwo.setText(String.valueOf(num2));
                break;
            case R.id.fabAdd3:
                num3 = Integer.parseInt(numberThree);
                num3++;
                this.numberThree.setText(String.valueOf(num3));
                break;
            case R.id.fabAdd4:
                num4 = Integer.parseInt(numberFour);
                num4++;
                this.numberFour.setText(String.valueOf(num4));
                break;
            case R.id.fabAdd5:
                num5 = Integer.parseInt(numberFive);
                num5++;
                this.numberFive.setText(String.valueOf(num5));
                break;
            case R.id.fabRemove1:
                num1 = Integer.parseInt(numberOne);
                if (num1 > 0) num1--;
                this.numberOne.setText(String.valueOf(num1));
                break;
            case R.id.fabRemove2:
                num2 = Integer.parseInt(numberTwo);
                if (num2 > 0) num2--;
                this.numberTwo.setText(String.valueOf(num2));
                break;
            case R.id.fabRemove3:
                num3 = Integer.parseInt(numberThree);
                if (num3 > 0) num3--;
                this.numberThree.setText(String.valueOf(num3));
                break;
            case R.id.fabRemove4:
                num4 = Integer.parseInt(numberFour);
                if (num4 > 0) num4--;
                this.numberFour.setText(String.valueOf(num4));
                break;
            case R.id.fabRemove5:
                num5 = Integer.parseInt(numberFive);
                if (num5 > 0) num5--;
                this.numberFive.setText(String.valueOf(num5));
                break;
            case R.id.fabOrdina:
                new FloatingActionButtonToDb(numberOne, numberTwo, numberThree, numberFour, numberFive, "antipasto");
            default:

        }
    }
}
