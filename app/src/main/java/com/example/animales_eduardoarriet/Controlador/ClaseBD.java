package com.example.animales_eduardoarriet.Controlador;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.animales_eduardoarriet.R;

import java.util.ArrayList;

public class ClaseBD extends SQLiteOpenHelper {

    //Creamos el constructor
    public ClaseBD(@Nullable Context context) {
        super(context, ConstantesBD.BD_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la tabla
        db.execSQL(ConstantesBD.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Borra la tabla si existe
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBD.TABLE_NAME);

        //La vuleve a crear
        onCreate(db);
    }

    public long insertarDatos(String icono, String nombre, String numeroPatas, String tipoAnimal, String descripcion){

        //Creamos BD para almacenar registros en la tabla
        SQLiteDatabase db = getWritableDatabase();


        //Creamos la fecha actual que va a ser la fecha de registro
        String time = "" + System.currentTimeMillis();

        //if(tipoAnimal.equals("Terrestre")){

        //}
        //Creamos un ContentValues con los valores a almacenar
        ContentValues valores = new ContentValues();
        Log.i("basededatos", icono);
        valores.put(ConstantesBD.C_ICONO, icono);
        valores.put(ConstantesBD.C_NAME, nombre);
        valores.put(ConstantesBD.C_NPATAS, numeroPatas);
        valores.put(ConstantesBD.C_TANIMAL, tipoAnimal);
        valores.put(ConstantesBD.C_DESCRIPCION, descripcion);
        valores.put(ConstantesBD.C_ADDEDTIME, time);

        long id =  db.insert(ConstantesBD.TABLE_NAME, null, valores);

        return id;
    }

    //Método que uso para mostrar todos los animales
    public ArrayList<Animales> mostrarAnimales(String cAddedtime){
        ArrayList<Animales> animales;
        animales = new ArrayList<>();

        String query = " SELECT * FROM " + ConstantesBD.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Animales animal = new Animales(
                        cursor.getInt(cursor.getColumnIndex(ConstantesBD.C_ID)) + "",
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_NAME)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_NPATAS)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_TANIMAL)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_DESCRIPCION))
                );
                animales.add(animal);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return animales;
    }

    //Método que uso para mostrar todos los animales
    public ArrayList<Animales> getAnimalesCondicion(String nombreColumn, String valor){
        ArrayList<Animales> animales;
        animales = new ArrayList<>();

        if(!nombreColumn.equals(null) && !valor.equals(null)) {
                String query = " SELECT * FROM " + ConstantesBD.TABLE_NAME + " WHERE " + nombreColumn + "=\'" + valor + "\'";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    Animales animal = new Animales(
                            cursor.getInt(cursor.getColumnIndex(ConstantesBD.C_ID)) + "",
                            cursor.getString(cursor.getColumnIndex(ConstantesBD.C_NAME)),
                            cursor.getString(cursor.getColumnIndex(ConstantesBD.C_NPATAS)),
                            cursor.getString(cursor.getColumnIndex(ConstantesBD.C_TANIMAL)),
                            cursor.getString(cursor.getColumnIndex(ConstantesBD.C_DESCRIPCION))
                    );
                    animales.add(animal);
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        return animales;
    }


    //Método para sacar los terrestres
    public ArrayList<Animales> getAnimalesTerrestres(){
        ArrayList<Animales> lista;
        lista = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        String terr = "SELECT * FROM " + ConstantesBD.TABLE_NAME + " WHERE " + ConstantesBD.C_TANIMAL + " LIKE " + "'Terrestre'";

        Cursor cursor = db.rawQuery(terr, null);

        if (cursor.moveToFirst()){
            do{
                Animales animal = new Animales(
                        cursor.getInt(cursor.getColumnIndex(ConstantesBD.C_ID)) + "",
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_NAME)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_NPATAS)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_TANIMAL)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_DESCRIPCION))

                );
                lista.add(animal);
            }while(cursor.moveToNext());
        }

            cursor.close();
            db.close();
            return lista;


    }

    //Método para editar
    public void editarAnimales(String id, String nombre, String numeroPatas, String tipoAnimal, String descripcion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        //valores.put(ConstantesBD.C_PHOTO, foto);
        valores.put(ConstantesBD.C_NAME, nombre);
        valores.put(ConstantesBD.C_NPATAS, numeroPatas);
        valores.put(ConstantesBD.C_TANIMAL, tipoAnimal);
        valores.put(ConstantesBD.C_DESCRIPCION, descripcion);


        db.update(ConstantesBD.TABLE_NAME,valores,""+ConstantesBD.C_ID+"="+id,null);
        db.close();

    }

    //Método para eliminar animales
    public void eliminarAnimales(String id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantesBD.TABLE_NAME, ConstantesBD.C_ID + " = ? ", new String[]{id});

        db.close();

    }
}
