package com.hgf.greatfitness.saludApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.hgf.greatfitness.R

class SaludActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salud)

        //Primero recuperar es el boton
        val btnPulsar = findViewById<Button>(R.id.btnPulsar)
        val edtTexto = findViewById<EditText>(R.id.edtTexto)

        btnPulsar.setOnClickListener {
            Log.i("RepositorioCompartido","------ BOTON ${btnPulsar.id} PULSADO ------")
            //val intent = Intent(this, RecibeSaludoActivity::class.java)
            //putExtra incluyendo la informacion que queramos pasar
            //intent.putExtra("texto",edtTexto.text.toString())
           // startActivity(intent)
        }
    }
}