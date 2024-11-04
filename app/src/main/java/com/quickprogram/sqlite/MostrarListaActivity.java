package com.quickprogram.sqlite;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.quickprogram.sqlite.Entidades.Persona;
import com.quickprogram.sqlite.viewmodel.PersonaViewModel;

import java.util.List;

public class MostrarListaActivity extends AppCompatActivity {

    private PersonaViewModel personaViewModel;
    private PersonaAdapter personaAdapter;
    private static final int REQUEST_CODE_EDITAR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_lista);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personaAdapter = new PersonaAdapter();
        recyclerView.setAdapter(personaAdapter);

        personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);
        personaViewModel.getListaDePersonas().observe(this, new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> persona) {
                personaAdapter.setPersonasList(persona);
            }
        });

        personaAdapter.setOnItemClickListener(position -> {
            Persona personaSeleccionada = personaAdapter.getPersonaAt(position);
            mostrarDialogoConfirmacionEdicion(personaSeleccionada);
        });

        personaAdapter.setOnItemLongClickListener(position -> {
            Persona personaSeleccionada = personaAdapter.getPersonaAt(position);
            mostrarDialogoConfirmacionEliminacion(personaSeleccionada);
        });
    }

    private void mostrarDialogoConfirmacionEdicion(Persona persona) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres modificar esta persona?")
                .setTitle("Confirmación")
                .setPositiveButton("Sí", (dialog, id) -> {
                    // Abrir la actividad EditarPersonaActivity con los datos de la persona seleccionada
                    Intent intent = new Intent(MostrarListaActivity.this, EditarPersonaActivity.class);
                    intent.putExtra("idPersona", persona.idPersona);
                    intent.putExtra("nombre", persona.nombrePersona);
                    intent.putExtra("apellido", persona.apellidosPersona);
                    intent.putExtra("edad", persona.edadPersona);
                    startActivityForResult(intent, REQUEST_CODE_EDITAR); // Usa onActivityResult para manejar el resultado
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void mostrarDialogoConfirmacionEliminacion(Persona persona) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres eliminar esta persona?")
                .setTitle("Confirmación")
                .setPositiveButton("Sí", (dialog, id) -> {
                    personaViewModel.delete(persona);
                    Toast.makeText(this, "Persona eliminada correctamente", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDITAR && resultCode == RESULT_OK) {
            int id = data.getIntExtra("idPersona", -1);
            String nombre = data.getStringExtra("nombre");
            String apellido = data.getStringExtra("apellido");
            int edad = data.getIntExtra("edad", -1);

            if (id != -1) {
                Persona personaActualizada = new Persona(id, nombre, apellido, edad);
                personaViewModel.update(personaActualizada); // Usa el método en tu ViewModel
            }
        }
    }
}