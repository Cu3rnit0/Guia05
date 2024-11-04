package com.quickprogram.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.quickprogram.sqlite.Entidades.Persona;
import com.quickprogram.sqlite.viewmodel.PersonaViewModel;

public class AgregarPersonaActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etEdad;
    private PersonaViewModel personaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_persona);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());
                if (nombre.isEmpty() || apellido.isEmpty() || edad < 0) {
                    Toast.makeText(v.getContext(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Persona persona = new Persona();

                persona.nombrePersona = nombre;
                persona.apellidosPersona = apellido;
                persona.edadPersona = edad;
                personaViewModel.insertPersona(persona);
                Toast.makeText(v.getContext(), "Persona agregada correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}