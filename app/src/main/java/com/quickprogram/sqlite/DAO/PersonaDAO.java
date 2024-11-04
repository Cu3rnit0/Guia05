package com.quickprogram.sqlite.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.quickprogram.sqlite.Entidades.Persona;
import java.util.*;
@Dao
public interface PersonaDAO {
    @Insert
    void Insert(Persona personaEntity);
    @Query("SELECT * FROM PERSONAS")
    LiveData<List<Persona>> obtenerTodasLasPersonas();
    @Delete
    void Delete(Persona personaEntity);
    @Update
    void Update(Persona personaEntity);
}
