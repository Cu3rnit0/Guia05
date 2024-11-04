package com.quickprogram.sqlite.Database;
import android.content.*;
import androidx.room.*;
import com.quickprogram.sqlite.DAO.PersonaDAO;
import com.quickprogram.sqlite.Entidades.Persona;

    @Database(entities = {Persona.class},version = 1)
public abstract class PersonaDataBase extends RoomDatabase {
    private static PersonaDataBase instance;
    public abstract PersonaDAO personaDAO();
    public static synchronized PersonaDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PersonaDataBase.class,"personadb").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    }
