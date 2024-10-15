package com.example.tracks

import Track
import TracksAPI
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button // Asegúrate de importar Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.tracks.TracksAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var tracksAPI: TracksAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Instanciar Retrofit aquí, fuera del listener
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/")  // Cambia a la URL de tu servicio
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        tracksAPI = retrofit.create(TracksAPI::class.java)

        getTracks()

        // Configurar el botón para agregar una pista
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            val intent = Intent(this, AddTrackActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getTracks() {
        // Llamada a la API para obtener la lista de pistas
        val call = tracksAPI.getAllTracks()
        call.enqueue(object : Callback<List<Track>> {
            override fun onResponse(call: Call<List<Track>>, response: Response<List<Track>>) {
                if (response.isSuccessful) {
                    val tracks = response.body() ?: emptyList()
                    // Actualiza el RecyclerView con la lista de pistas
                    updateRecyclerView(tracks)
                } else {
                    // Manejar el error
                    Log.e("MainActivity", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Track>>, t: Throwable) {
                // Manejar el error
                Log.e("MainActivity", "Error en la llamada: ${t.message}")
            }
        })
    }

    private fun updateRecyclerView(tracks: List<Track>) {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view) // Asegúrate de que tu RecyclerView tenga este ID
        val adapter = TracksAdapter(tracks)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this) // Usa un LinearLayoutManager
    }
}