package com.hgf.greatfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.hgf.greatfitness.dietaApp.DietaActivity
import com.hgf.greatfitness.ejerciciosApp.EjercicioActivity
import com.hgf.greatfitness.RutinasApp.RutinasActivity
import com.hgf.greatfitness.saludApp.SaludActivity

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Capturamos botones y lanzamos
        val btnSalud= findViewById<Button>(R.id.btnSalud)
        val btnDieta= findViewById<Button>(R.id.btnDieta)
        val btnEjercicios= findViewById<Button>(R.id.btnEjercicios)
        val btnVideoRutinas= findViewById<Button>(R.id.btnVideoRutinas)


        //Eventos
        btnSalud.setOnClickListener {
            Log.i("MENU --------- ","LANZAMOS APP IMC")
            navegarHaciaApp(SaludActivity::class.java)
        }
        btnDieta.setOnClickListener {
            Log.i("MENU --------- ","LANZAMOS SALUDA APP")
            navegarHaciaApp(DietaActivity::class.java)
        }
        btnEjercicios.setOnClickListener {
            Log.i("MENU --------- ","LANZAMOS IMC MEJORADO")
            navegarHaciaApp(EjercicioActivity::class.java)
        }
        btnVideoRutinas.setOnClickListener {
            Log.i("MENU --------- ","LANZAMOS CANALES APP")
            navegarHaciaApp(RutinasActivity::class.java)
        }
    }
    fun navegarHaciaApp(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}