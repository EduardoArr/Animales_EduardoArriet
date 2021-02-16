package com.example.animales_eduardoarriet.Controlador;

public class Animales {
    String id;
    String icono;
    String nombre;
    String nPatas;
    String tAnimal;
    String descripcion;


    public Animales(String id, String nombre, String nPatas, String tAnimal, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.nPatas = nPatas;
        this.tAnimal = tAnimal;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getnPatas() {
        return nPatas;
    }

    public void setnPatas(String nPatas) {
        this.nPatas = nPatas;
    }

    public String gettAnimal() {
        return tAnimal;
    }

    public void settAnimal(String tAnimal) {
        this.tAnimal = tAnimal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
