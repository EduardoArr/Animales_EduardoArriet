package com.example.animales_eduardoarriet.Modelo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.animales_eduardoarriet.Controlador.Animales;
import com.example.animales_eduardoarriet.Controlador.ClaseBD;
import com.example.animales_eduardoarriet.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Agregar_Animal extends AppCompatActivity {

    EditText nombre;
    EditText nPatas;
    EditText tAnimal;
    EditText descripcion;
    FloatingActionButton aceptar;

    ActionBar actionBar;
    Animales a;

    //Declaramos variables para guardar datos
    Uri uri;
    String  txt_icono, txt_nombre, txt_nPatas, txt_tAnimal, txt_descripcion, txt_id;

    ClaseBD claseBD;

    boolean editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__animal);

        nombre = findViewById(R.id.nombre);
        nPatas = findViewById(R.id.npatas);
        tAnimal = findViewById(R.id.tanimal);
        descripcion = findViewById(R.id.descrip);
        aceptar = findViewById(R.id.save);

        //Cambiamos el titulo del ActionBar y le ponemos un botón para volver
        actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar animal al Zoo");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        claseBD = new ClaseBD(this);

        //ESTO ES PARA QUE NOS APAREZCAN LOS DATOS CUANDO LE DAMOS A EDITAR PERO ME DA ERROR.
       Bundle bundle = getIntent().getExtras();
       editar = bundle.getBoolean("REQUEST_EDITAR");
       Log.i("EDITAR", "" + editar);

        if (editar) {
            //cambiamos el título del ActionBar
            actionBar.setTitle("Editar Animal del Zoo");

            //cojo los datos del animal
            txt_nombre = bundle.getString("nombre");
            txt_id = bundle.getString("id");
            txt_nPatas = bundle.getString("nPatas");
            txt_tAnimal = bundle.getString("tAnimal");
            txt_descripcion = bundle.getString("descripcion");

            //y los muestro
            nombre.setText(txt_nombre);
            nPatas.setText(txt_nPatas);
            tAnimal.setText(txt_tAnimal);
            descripcion.setText(txt_descripcion);

        } else {
            //cambiamos el título del ActionBar
            actionBar.setTitle("Agregar animal al Zoo");
        }

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAnimales();
                //Para que vuelva atras una vez guardado los datos.
                onBackPressed();
            }
        });
    }
//con esto guardamos los datos de los animales
    private void guardarAnimales() {

        //Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                //+ "://" + this.getResources().getResourceName(R.drawable.conejo)
                //+ '/' + this.getResources().getResourceTypeName(R.drawable.conejo)
                //+ '/' + this.getResources().getResourceEntryName(R.drawable.conejo)
        //);


        txt_icono = getResources().getResourceName(R.drawable.conejo);
        txt_nombre = nombre.getText().toString().trim();
        txt_nPatas = nPatas.getText().toString().trim();
        txt_tAnimal = tAnimal.getText().toString().trim();
        txt_descripcion = descripcion.getText().toString().trim();

        String timestamp = "" + System.currentTimeMillis();
        long id;
        //Esto es para que cuando le demos a editar use el metodo editarAnimales
        if (!editar) {
            id = claseBD.insertarDatos(txt_icono, txt_nombre, txt_nPatas, txt_tAnimal, txt_descripcion);
        } else {
            claseBD.editarAnimales(txt_id, txt_nombre, txt_nPatas, txt_tAnimal, txt_descripcion);
        }
    }

    //Para que desde el ActionBar te vaya a la actividad anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}