package com.example.animales_eduardoarriet.Controlador;

public class ConstantesBD {
    //Nombre de la BD
    public static final String BD_NAME = "ANIMALES";

    //Versión de la BD
    public static  final int BD_VERSION = 1;

    //Nombre de la tabla
    public static final String TABLE_NAME = "ANIMAL";

    //Campos de la tabla
    public static final String C_ID = "ID";
    public static final String C_ICONO = "ICONO";
    public static final String C_NAME = "NOMBRE";
    public static final String C_NPATAS = "NPATAS";
    public static final String C_TANIMAL = "TANIMAL";
    public static final String C_DESCRIPCION = "DESCRIPCION";
    public static final String C_ADDEDTIME = "FECHA_ALTA";

    //Código de creación de la tabla
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_ICONO + " TEXT, "
            + C_NAME + "  TEXT, "
            + C_NPATAS + " TEXT, "
            + C_TANIMAL + " TEXT, "
            + C_DESCRIPCION + " TEXT, "
            + C_ADDEDTIME + " TEXT"
            + ")";
}
