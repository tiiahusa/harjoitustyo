package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import lutemonfarm.Black;
import lutemonfarm.Green;
import lutemonfarm.Lutemon;
import lutemonfarm.Orange;
import lutemonfarm.Pink;
import lutemonfarm.White;

public class AddNewLutemon extends AppCompatActivity {

    private EditText tbLutemonsName;
    private RadioGroup rgColors;
    private Context context;
    private Lutemon lutemon;
    private Storage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lutemon);

        // Link code to radiogroup + textbox
        tbLutemonsName = findViewById(R.id.tbName);
        rgColors = findViewById(R.id.rgColors);

        context = AddNewLutemon.this;
        storage = Storage.getInstance();

    }

    public void AddLutemon(View view) {

        if(tbLutemonsName.getText().length() > 0) { // Check that name input isn't empty
            if(rgColors.getCheckedRadioButtonId() != -1) { // Check that color is chosen

                switch (rgColors.getCheckedRadioButtonId()) { // get radiobutton id..
                    case R.id.rbBlack: // if it is black, create black lutemon
                        lutemon = new Black(tbLutemonsName.getText().toString(), "Musta");
                        break;
                    case R.id.rbGreen: // if it is green, create green lutemon
                        lutemon = new Green(tbLutemonsName.getText().toString(), "Vihreä");
                        break;
                    case R.id.rbOrange: // if it is orange, create orange lutemon
                        lutemon = new Orange(tbLutemonsName.getText().toString(), "Oranssi");
                        break;
                    case R.id.rbPink: // if it is pink, create pink lutemon
                        lutemon = new Pink(tbLutemonsName.getText().toString(), "Pinkki");
                        break;
                    case R.id.rbWhite: // if it is white, create white lutemon
                        lutemon = new White(tbLutemonsName.getText().toString(), "Valkoinen");
                        break;
                }
                storage.AddLutemon(lutemon); // Add lutemon to storage

                // Go back to main page
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);


            } else Toast.makeText(context, "Muista valita Lutemonin väri!", Toast.LENGTH_LONG).show(); //If color not chosen
        } else Toast.makeText(context, "Lutemonille täytyy antaa ensin nimi!", Toast.LENGTH_LONG).show(); // If name is empty




    }
}
