package com.quickprogram.sqlite.DAO;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.liveData;
import androidx.room.*;

import com.quickprogram.sqlite.Entidades.Persona;
import java.util.*;
@Dao
public interface PersonaDAO {
    @Insert
    voidi Insert(Persona personaEntity);
    @Query("Select * From PERSONAS")
    LiveData<List<Persona>> obtenerTodasLasPersonas();

}
