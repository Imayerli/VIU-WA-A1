package com.hgf.greatfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.hgf.greatfitness.dietaApp.DietaActivity
import com.hgf.greatfitness.ejerciciosApp.EjercicioActivity
import com.hgf.greatfitness.RutinasApp.RutinasActivity
import com.hgf.greatfitness.saludApp.SaludActivity

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val backgroundColor = ContextCompat.getColor(this, R.color.amarillo)

        //Capturamos botones y lanzamos
        val btnSalud= findViewById<Button>(R.id.btnSalud)
        val btnDieta= findViewById<Button>(R.id.btnDieta)
        val btnEjercicios= findViewById<Button>(R.id.btnEjercicios)
        val btnVideoRutinas= findViewById<Button>(R.id.btnVideoRutinas)
        btnSalud.setBackgroundColor(backgroundColor)
        btnDieta.setBackgroundColor(backgroundColor)
        btnEjercicios.setBackgroundColor(backgroundColor)
        btnVideoRutinas.setBackgroundColor(backgroundColor)

        //Eventos
        btnSalud.setOnClickListener {
            Log.i("MENU --------- ","LANZAMOS SALUD")
            navegarHaciaApp(SaludActivity::class.java)
        }
        btnDieta.setOnClickListener {
            Log.i("MENU --------- ","LANZAMOS DIETA")
            navegarHaciaApp(DietaActivity::class.java)
        }
        btnEjercicios.setOnClickListener {
            Log.i("MENU --------- ","LANZAMOS EJERCICIO")
            navegarHaciaApp(RutinasActivity::class.java)
        }
        btnVideoRutinas.setOnClickListener {
            Log.i("MENU --------- ","LANZAMOS RUTINAS")
            navegarHaciaApp(EjercicioActivity::class.java)
        }

    }
    fun navegarHaciaApp(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}