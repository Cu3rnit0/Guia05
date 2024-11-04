package com.quickprogram.sqlite.Entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.*;

@Entity
public class Persona implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int idPersona;
    public String nombrePersona;
    public String apellidosPersona;
    public int edadPersona;

    public Persona(){

    }
    public  Persona(String nombrePersona,String apellidosPersona, int edadPersona){
        this.nombrePersona =nombrePersona;
        this.apellidosPersona = apellidosPersona;
        this.edadPersona = edadPersona;
    }

    public Persona(int idPersona, String nombrePersona, String apellidosPersona, int edadPersona){
        this.idPersona= idPersona;
        this.nombrePersona= nombrePersona;
        this.apellidosPersona = apellidosPersona;
        this.edadPersona= edadPersona;
    }

@NonNull
@Override
public String toString(){
return nombrePersona+""+apellidosPersona+""+edadPersona;}

}
