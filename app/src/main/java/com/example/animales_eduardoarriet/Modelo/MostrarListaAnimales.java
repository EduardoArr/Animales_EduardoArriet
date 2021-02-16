package com.example.animales_eduardoarriet.Modelo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.animales_eduardoarriet.Controlador.AdaptadorAnimales;
import com.example.animales_eduardoarriet.Controlador.Animales;
import com.example.animales_eduardoarriet.Controlador.ClaseBD;
import com.example.animales_eduardoarriet.Controlador.ConstantesBD;
import com.example.animales_eduardoarriet.R;

import java.util.ArrayList;

public class MostrarListaAnimales extends AppCompatActivity {

    RecyclerView animales;

    ActionBar actionBar;



    String ordenarPorNombreAsc = ConstantesBD.C_NAME + "ASC";
    String ordenarPorNombreDesc = ConstantesBD.C_NAME + "DESC";
    String ordenarPorNuevo = ConstantesBD.C_ADDEDTIME + "Desc";

    String condicionOrdenarActual = ordenarPorNuevo;

    ClaseBD claseBD;

    boolean tdos;
    String columna;
    String valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista_animales);
        animales = findViewById(R.id.animales);

        Bundle bundle = getIntent().getExtras();
        tdos = bundle.getBoolean("MOSTRAR TODOS");
        claseBD = new ClaseBD(this);

        if(tdos){
            actionBar = getSupportActionBar();
            actionBar.setTitle("Mostrar TODOS");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            mostrarAnimales(condicionOrdenarActual, false, null, null);
        }else{

            columna = bundle.getString("nombreCol");
            valor = bundle.getString("valor");
            mostrarAnimales(condicionOrdenarActual, true, columna, valor);
        }
        //if(terrestre){
            //actionBar = getSupportActionBar();
            //actionBar.setTitle("SOLO TERRESTRES");
            //actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setDisplayHomeAsUpEnabled(true);
            //mostrarTerrestres(condicionOrdenarActual);

        //}

    }

    private void mostrarAnimales(String condicionOrdenar, boolean condicion, String col, String val){

        AdaptadorAnimales adapter;
        condicionOrdenarActual = condicionOrdenar;
        if(condicion == false){
            adapter = new AdaptadorAnimales(this, claseBD.mostrarAnimales(condicionOrdenar));

        }else{
            adapter = new AdaptadorAnimales(this, claseBD.getAnimalesCondicion(col, val));
        }
        animales.setAdapter(adapter);
    }

    /*private void mostrarTerrestres(String condicionOrdenar){
        condicionOrdenarActual = condicionOrdenar;

        AdaptadorAnimales adapter = new AdaptadorAnimales(this, claseBD.getAnimalesTerrestres());

        animales.setAdapter(adapter);
    }*/

    public void onResume(){
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        tdos = bundle.getBoolean("MOSTRAR TODOS");
        if(tdos){
            actionBar = getSupportActionBar();
            actionBar.setTitle("Mostrar TODOS");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            mostrarAnimales(condicionOrdenarActual, false, null, null);
        }else{

            columna = bundle.getString("nombreCol");
            valor = bundle.getString("valor");
            mostrarAnimales(condicionOrdenarActual, true, columna, valor);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}