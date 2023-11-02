package com.hgf.greatfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.hgf.greatfitness.data.Api
import com.hgf.greatfitness.data.InicioSesionRequest
import com.hgf.greatfitness.data.InicioSesionResponse
import com.hgf.greatfitness.databinding.ActivitySessionBinding
import retrofit2.Call
import retrofit2.Response

class SessionActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener{
    private lateinit var mBinding:ActivitySessionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySessionBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.btnRegistrarse.setOnClickListener(this)
        mBinding.btnIniSesion.setOnClickListener(this)
        mBinding.emailtxt.onFocusChangeListener = this
        mBinding.passwordtxt.onFocusChangeListener = this


    }

    /**
     *  Funcion para validacion de campos
     */
    private fun validateEmail(): Boolean {

        var error:String? = null
        val value: String = mBinding.emailtxt.text.toString()
        if (value.isEmpty()){
            error =  "Correo electronico es requerido"
        }else if (Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            error = "Correo electronico es invalido"

        }

        mBinding.email.apply{
            isErrorEnabled = true
            error = error

        }

        return error == null

    }

    /**
     *  Funcion para validacion de campos
     */
    private fun validatePassword(): Boolean {
        var error:String? = null
        val value: String = mBinding.passwordtxt.text.toString()
        if (value.isEmpty()){
            error =  "Conitraseña es requerido"
        }else if (value.length < 6){
            error = "cContraseña es invalido"

        }
        mBinding.password.apply{
            isErrorEnabled = true
            error = error

        }

        return error == null

    }

    /**
     *  Funcion Onclick
     */
    override fun onClick(view: View?) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        if (view != null && view.id == R.id.btnRegistrarse){
            navegarHaciaApp(RegisterActivity::class.java)
        }
        if (view != null && view.id == R.id.btnIniSesion){
            val inicioSesionRequest = InicioSesionRequest(
                mBinding.emailtxt.text.toString()
                , mBinding.passwordtxt.text.toString())
            val request = Api.build().iniciarSesion(inicioSesionRequest)

            request.enqueue(object:  retrofit2.Callback<InicioSesionResponse> {
                override fun onResponse(call: Call<InicioSesionResponse>, response: Response<InicioSesionResponse>) {
                    val usuarioResponse = response.body()
                    if (usuarioResponse != null) {
                            if (usuarioResponse.msg.equals("Bienvenido")){
                            navegarHaciaApp(HomeActivity::class.java)
                        }
                    }
                    val error = response.errorBody()
                    if (error != null){
                        var gson = Gson()
                        var testModel = gson.fromJson(error.string(), InicioSesionResponse::class.java)
                        alertDialogBuilder.setMessage(testModel.msg)
                        alertDialogBuilder.show()
                    }
                }
                override fun onFailure(call: Call<InicioSesionResponse>, t: Throwable) {
                    alertDialogBuilder.setMessage("No se pudo iniciar Sesion Correctamente")
                    alertDialogBuilder.show()
                }

            })

        }

    }




    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null){
            when(view.id){
                R.id.emailtxt -> {
                    if (hasFocus){
                        if (mBinding.email.isCounterEnabled){
                            mBinding.email.isErrorEnabled = false
                        }
                    }else{
                        validateEmail()
                    }

                }
                R.id.passwordtxt ->{
                    if (hasFocus){
                        if (mBinding.password.isCounterEnabled){
                            mBinding.password.isErrorEnabled = false
                        }
                    }else{
                        validatePassword()
                    }

                }
            }

        }
    }

    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
        TODO("Not yet implemented")
    }

    fun navegarHaciaApp(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}