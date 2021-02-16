package com.example.animales_eduardoarriet.Controlador;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animales_eduardoarriet.Modelo.Agregar_Animal;
import com.example.animales_eduardoarriet.Modelo.MainActivity;
import com.example.animales_eduardoarriet.Modelo.MostrarListaAnimales;
import com.example.animales_eduardoarriet.R;

import java.util.ArrayList;

public class AdaptadorAnimales extends RecyclerView.Adapter<AdaptadorAnimales.HolderAnimales>  {

    private Context contexto;
    private ArrayList<Animales> animales;

    //Objeto de tipo BD
    ClaseBD claseBD;

    //Creamos el constructor
    public AdaptadorAnimales(Context context, ArrayList<Animales> animales){
        this.contexto = context;
        this.animales = animales;
        claseBD = new ClaseBD(contexto);
    }

    @NonNull
    @Override
    public HolderAnimales onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflamos el layout
        View view = LayoutInflater.from(contexto).inflate(R.layout.lista, parent, false);


        return new HolderAnimales(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAnimales holder, int position) {
//Con este método obtenemos datos, los establecemos y vemos los clicks

        //Primero obtenemos los datos de cada alumno por la posición
        Animales animal = animales.get(position);
        final String id = animal.getId();

        final String nombre = animal.getNombre();
        final String nPatas = animal.getnPatas();
        final String icono = animal.getIcono();
        final String tAnimal = animal.gettAnimal();
        final String descripcion = animal.getDescripcion();



        //Estos datos los mostramos en las vistas correspondientes de lista_alumno que están recogidas en el holder
        holder.nombre.setText(nombre);;

        //para la imagen, si el usuario no quiere asignar imagen, la uri será nula por lo que configuramos una imagen predeterminada para este caso
        if(icono == null){
           Log.i("hola", "ICONO NULL");
           holder.imagen.setImageResource(R.drawable.ic_baseline_insert_photo_24);
        }else{
            holder.imagen.setImageURI(Uri.parse(icono));
        }


        //Si clickamos en el botón de opciones
        holder.btn_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesDialogo(""+position, id, nombre, nPatas, tAnimal, descripcion);
            }
        });


    }

    //Hacemos un método para mostrar el diálogo del botón de editar y borrar
    public void mostrarOpcionesDialogo(final String posicion, final String id, final String nombre, final String nPatas, final String tAnimal, final String descripcion){
        //Creamos un array de Strings con las opciones que van a aparecer en el diálogo

        String[] opciones = {"Editar", "Eliminar"};

        //Creamos el AlertDialog con las opciones
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setTitle("Selecciona una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //El which a 0 indica la primera opción que es Editar
                if(which == 0){

                    Intent intent = new Intent(contexto, Agregar_Animal.class);
                    intent.putExtra("id", id);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("nPatas", nPatas);
                    intent.putExtra("tAnimal", tAnimal);
                    intent.putExtra("descripcion", descripcion);

                    //Añadimos otro dato para saber si viene de editar
                    intent.putExtra("REQUEST_EDITAR", true);
                    contexto.startActivity(intent);
                }
                //Si which es 1 ha clickado en eliminar
                else if(which == 1){
                    //Creamos otro diálogo para ver si estamos seguros de borrarlo
                    AlertDialog.Builder eliminarDialogo = new AlertDialog.Builder(contexto);
                    eliminarDialogo.setTitle("Eliminarás un alumno");
                    eliminarDialogo.setMessage("¿Estás seguro de eliminarlo?");
                    eliminarDialogo.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //en este caso borramos al alumno que queremos
                            claseBD.eliminarAnimales(id);
                            //y actualizamos la lista de alumnos otra vez llamando al método onResume del MainActivity
                            ((MostrarListaAnimales)contexto).onResume();
                        }
                    });
                    eliminarDialogo.setNegativeButton("No", null);
                    eliminarDialogo.create().show();

                }
            }
        });

        //Creamos y mostramos el diálogo
        builder.create().show();

    }

    @Override
    public int getItemCount() {
        return animales.size();
    }

    public class HolderAnimales extends RecyclerView.ViewHolder{
        //En esta clase cogemos todos los elementos de lista_alumnos para poder utilizarlos en AdapterAlumno y rellenar el recyclerview posteriormente
        ImageView imagen;
        TextView nombre;
        ImageButton btn_mas;

        public HolderAnimales(@NonNull View itemView) {
            super(itemView);

            //Inicializamos los elementos de la vista
            imagen = itemView.findViewById(R.id.icono);
            nombre = itemView.findViewById(R.id.tv_nombre);
            btn_mas = itemView.findViewById(R.id.btn_opciones);

        }
    }

}
