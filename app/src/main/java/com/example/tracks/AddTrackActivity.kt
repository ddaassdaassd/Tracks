package com.example.tracks

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddTrackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_track)
        enableEdgeToEdge()

        val editTextTitle: EditText = findViewById(R.id.editTextTitle)
        val editTextArtist: EditText = findViewById(R.id.editTextArtist)
        val buttonAddTrack: Button = findViewById(R.id.buttonAddTrack)

        // Configurar el listener para el botón después de definir los elementos de la vista
        buttonAddTrack.setOnClickListener {
            val title = editTextTitle.text.toString()
            val artist = editTextArtist.text.toString()
            // Aquí puedes realizar la llamada a la API para agregar la pista
        }

        // Configurar el listener de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // Asegúrate de retornar insets aquí
        }
    }
}