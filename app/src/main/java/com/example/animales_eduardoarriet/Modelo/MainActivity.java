package com.example.animales_eduardoarriet.Modelo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.animales_eduardoarriet.Controlador.ClaseBD;
import com.example.animales_eduardoarriet.Controlador.ConstantesBD;
import com.example.animales_eduardoarriet.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ImageView todos;
    ImageView acuatico;
    ImageView terrestre;
    ImageView aereo;
    Button pregunta;
    FloatingActionButton addItem;

    ActionBar actionBar;

    ClaseBD claseBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todos = findViewById(R.id.animales);
        acuatico = findViewById(R.id.acuatico);
        terrestre = findViewById(R.id.terrestre);
        aereo = findViewById(R.id.aereo);
        pregunta = findViewById(R.id.pregunta);
        addItem = findViewById(R.id.btn_add);

        claseBD = new ClaseBD(this);
        //Cambiamos el titulo del actionbar
        actionBar = getSupportActionBar();
        actionBar.setTitle("¡Bienvenido al Zoo de DAM!");

        //Esto lo uso para cambiar de ventana a la de agregar un animal.
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Agregar_Animal.class);
                intent.putExtra("REQUEST_EDITAR", false);
                startActivity(intent);
            }
        });

        //para entrar a los terrestres
        terrestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MostrarListaAnimales.class);
                intent.putExtra("nombreCol", ConstantesBD.C_TANIMAL);
                intent.putExtra("valor", "Terrestre");
                intent.putExtra("MOSTRAR TODOS", false);
                startActivity(intent);
            }
        });

        //para entrar a todos
        todos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MostrarListaAnimales.class);
                intent.putExtra("MOSTRAR TODOS", true);
                startActivity(intent);
            }
        });

        //para entrar al alert
        pregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();

            }
        });

    }

    //Alert de el boton con la pregunta
    private void alert(){
        String[] opciones = {"Sin Patas", "De dos patas", "De cuatro patas"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Elige una opción");

        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    Intent intent = new Intent(MainActivity.this, MostrarListaAnimales.class);
                    intent.putExtra("nombreCol", ConstantesBD.C_NPATAS);
                    intent.putExtra("valor", "0");
                    intent.putExtra("MOSTRAR TODOS", false);
                    startActivity(intent);
                }
                if (which == 1){
                    Intent intent = new Intent(MainActivity.this, MostrarListaAnimales.class);
                    intent.putExtra("nombreCol", ConstantesBD.C_NPATAS);
                    intent.putExtra("valor", "2");
                    intent.putExtra("MOSTRAR TODOS", false);
                    startActivity(intent);
                }

                if (which == 2){
                    Intent intent = new Intent(MainActivity.this, MostrarListaAnimales.class);
                    intent.putExtra("nombreCol", ConstantesBD.C_NPATAS);
                    intent.putExtra("valor", "4");
                    intent.putExtra("MOSTRAR TODOS", false);
                    startActivity(intent);
                }
            }
        });
        builder.create().show();
    }
}