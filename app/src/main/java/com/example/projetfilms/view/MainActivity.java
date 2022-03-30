package com.example.projetfilms.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.projetfilms.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private SeekBar seekbar;
    private TextView seekbar_valeur;
    private Button research;
    private TextView mot;
    private EditText date;
    private String url;
    private String api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = "737693c04656fdf512002c5741874c7d";
        url = "www.themoviedb.org";

        mot = findViewById(R.id.mot);
        date = findViewById(R.id.date_nombre);

        ArrayList<String> data = new ArrayList<>();
        spinner = findViewById(R.id.spinner);
        data.add("Action");
        data.add("Animation");
        data.add("Aventure");
        data.add("Horreur");
        data.add("Comédie");
        data.add("Fantastique");
        data.add("Science-Fiction");
        ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data);
        spinner.setAdapter(aa);

        seekbar = findViewById(R.id.seekBar);
        seekbar_valeur = findViewById(R.id.seekbar_valeur);
        seekbar_valeur.setText("0");
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String test = String.valueOf(seekbar.getProgress());
                seekbar_valeur.setText(test);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        research = findViewById(R.id.research);
        research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ion.with(view.getContext())
                        .load("https://api.themoviedb.org/3/search/movie?api_key="+api+"&language=en-US&page=1&include_adult=false&query="+mot.getText().toString()+"&language=fr-FR&year="+date.getText().toString())
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                // do stuff with the result or error
                                mot.setText(result.toString());
                            }
                        });
                //Intent intent = new Intent(MainActivity.this, Secondaire.class);
                //startActivity(intent);
            }
        });







    }


}