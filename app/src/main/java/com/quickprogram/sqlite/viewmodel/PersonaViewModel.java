package com.quickprogram.sqlite.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.quickprogram.sqlite.DAO.PersonaDAO;
import com.quickprogram.sqlite.Database.PersonaDataBase;
import com.quickprogram.sqlite.Entidades.Persona;

import java.util.*;


public class PersonaViewModel extends AndroidViewModel {
    private PersonaDAO personaDAO;
    private LiveData<List<Persona>> listaDePersonas;
    public PersonaViewModel(@NonNull Application application) {
        super(application);

        PersonaDataBase database = PersonaDataBase.getInstance(application);


        personaDAO = database.personaDAO();

        listaDePersonas = personaDAO.obtenerTodasLasPersonas();
    }

    public LiveData<List<Persona>> getListaDePersonas() {
        return listaDePersonas;
    }

    public void insertPersona(Persona persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.Insert(persona);
            }
        }).start();
    }
    public void update(Persona persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.Update(persona);
            }
        }).start();
    }
    public void delete(Persona persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.Delete(persona);
            }
        }).start();
    }
}
