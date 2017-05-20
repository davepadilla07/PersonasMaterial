package com.example.cuc.personasmaterial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by CUC on 20/05/2017.
 */

public class Persona {
    private String foto;
    private String cedula;
    private String nombre;
    private String apellido;


    public Persona(String foto, String cedula, String nombre, String apellido) {
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;

    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public void guardar(Context contexto){
        //Declarar variables
        SQLiteDatabase db;
        String sql;

        //Abrir conexion base de datos en escritura
        PersonasSQLiteOpenHelper aux = new PersonasSQLiteOpenHelper(contexto,"DBPersonas",null);
        db=aux.getWritableDatabase();

        //Insertar forma 1
        sql="INSERT INTO Personas values('"
                +this.getFoto()+"','"
                +this.getCedula()+"','"
                +this.getNombre()+"','"
                +this.getApellido()+"')";

        db.execSQL(sql);

        //Insertar forma 2
        /*ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("foto",this.getFoto());
        nuevoRegistro.put("cedula",this.getCedula());
        nuevoRegistro.put("nombre",this.getNombre());
        nuevoRegistro.put("apellido",this.getApellido());


        db.insert("Personas",null,nuevoRegistro);*/

        //cerrar conexion
        db.close();
    }

    public void eliminar(Context contexto) {
        //Declarar variables
        SQLiteDatabase db;
        String sql;

        //Abrir conexion base de datos en escritura
        PersonasSQLiteOpenHelper aux = new PersonasSQLiteOpenHelper(contexto, "DBPersonas", null);
        db = aux.getWritableDatabase();

        //Insertar forma 1
        sql = "DELETE FROM Personas where cedula='" + this.getCedula() + "'";

        db.execSQL(sql);

        db.close();
    }

    public void modificar(Context contexto){
        //Declarar variables
        SQLiteDatabase db;
        String sql;

        //Abrir conexion base de datos en escritura
        PersonasSQLiteOpenHelper aux = new PersonasSQLiteOpenHelper(contexto,"DBPersonas",null);
        db=aux.getWritableDatabase();

        //Insertar forma 1
        sql="UPDATE Personas"+" SET nombre='"+this.getNombre()+"',"+" apellido='"+this.getApellido()+"'"+" where cedula='"+this.getCedula()+"'";

        db.execSQL(sql);

        db.close();
    }

}