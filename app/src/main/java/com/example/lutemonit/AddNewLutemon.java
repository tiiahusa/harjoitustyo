package com.example.lutemonit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddNewLutemon extends AppCompatActivity {

    private EditText tbLutemonsName;
    private RadioGroup rgColors;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lutemon);

        // Link code to radiogroup + textbox
        tbLutemonsName = findViewById(R.id.tbName);
        rgColors = findViewById(R.id.rgColors);

        context = AddNewLutemon.this;

    }

    public void AddLutemon(View view) {

        if(tbLutemonsName.getText().length() > 0) { // Check that name input isn't empty
            if(rgColors.getCheckedRadioButtonId() != -1) { // Check that color is chosed

                switch (rgColors.getCheckedRadioButtonId()) {
                    case

                }

                // Go back to main page
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);


            } else Toast.makeText(context, "Muista valita Lutemonin väri!", Toast.LENGTH_LONG).show(); //If color not chosed
        } else Toast.makeText(context, "Lutemonille täytyy antaa ensin nimi!", Toast.LENGTH_LONG).show(); // If name is empty




    }
}
}