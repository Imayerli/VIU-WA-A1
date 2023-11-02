package com.hgf.greatfitness.RutinasApp

import GeneralItemAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.hgf.greatfitness.data.Api
import com.hgf.greatfitness.data.GeneralidadResponse
import com.hgf.greatfitness.databinding.ActivityRutinasBinding
import retrofit2.Call
import retrofit2.Response

class RutinasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRutinasBinding
    private lateinit var generalItemAdapter: GeneralItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutinasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        cargarDatos()
    }

    private fun setUpRecyclerView() {
        generalItemAdapter = GeneralItemAdapter(listOf())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RutinasActivity)
            adapter = generalItemAdapter
        }
        Log.d("RutinasActivity", "RecyclerView configurado") // Log para RecyclerView
    }

    private fun cargarDatos() {
        val request = Api.build().obtenerGeneralidad("routine")

        request.enqueue(object: retrofit2.Callback<List<GeneralidadResponse>> {
            override fun onResponse(
                call: Call<List<GeneralidadResponse>>,
                response: Response<List<GeneralidadResponse>>
            ) {
                Log.d("SaludActivity", "Respuesta API: ${response.body()}") // Log para la respuesta de la API

                if (response.isSuccessful) {
                    val listaGeneralidad = response.body()
                    if (listaGeneralidad != null) {
                        generalItemAdapter.items = listaGeneralidad
                        generalItemAdapter.notifyDataSetChanged()
                    }
                } else {
                    // Manejo de error
                }
            }

            override fun onFailure(call: Call<List<GeneralidadResponse>>, t: Throwable) {
                // Manejo de fallo en la llamada
                Log.d("SaludActivity", "Respuesta API error: ${t.message}}") // Log para la respuesta de la API

            }
        })
    }

}