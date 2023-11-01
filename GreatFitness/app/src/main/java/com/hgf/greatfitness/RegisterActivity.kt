package com.hgf.greatfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.hgf.greatfitness.data.Api
import com.hgf.greatfitness.data.UsuarioRequest
import com.hgf.greatfitness.data.UsuarioResponse
import com.hgf.greatfitness.databinding.ActivityRegisterBinding
import org.chromium.base.Callback
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener{

    private lateinit var mBinding:ActivityRegisterBinding
    private lateinit var messague:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(R.layout.activity_register)
        mBinding.documentTypeTex.onFocusChangeListener = this
        mBinding.documentNumberTex.onFocusChangeListener = this
        mBinding.fullNameTxt.onFocusChangeListener = this
        mBinding.lastNametxt.onFocusChangeListener = this
        mBinding.addresstxt.onFocusChangeListener = this
        mBinding.emailtxt.onFocusChangeListener = this
        mBinding.passwordtxt.onFocusChangeListener = this
        mBinding.btnRegistrarAct.setOnClickListener(this)

        init()
    }

    private fun init(){
        messague = ""


    }

    private fun validateFullName(): Boolean {
        var error:String? = null
        val value: String = mBinding.fullNameTxt.text.toString()
        if (value.isEmpty()){
            error =  "Nombre es requerido"
        }
        mBinding.fullName.apply{
            isErrorEnabled = true
            error = error

        }

        return error == null

    }

    private fun validateLastName(): Boolean {
        var error:String? = null
        val value: String = mBinding.lastNametxt.text.toString()
        if (value.isEmpty()){
            error =  "Apellido es requerido"
        }
        mBinding.lastName.apply{
            isErrorEnabled = true
            error = error

        }
        return error == null

    }

    private fun validateDocumentType(): Boolean {
        var error:String? = null
        val value: String = mBinding.documentTypeTex.text.toString()
        if (value.isEmpty()){
            error =  "Tipo de documento es requerido"
        }
        mBinding.documentType.apply{
            isErrorEnabled = true
            error = error
        }
        return error == null

    }

    private fun validateDocumentNumber(): Boolean {
        var error:String? = null
        val value: String = mBinding.documentNumberTex.text.toString()
        if (value.isEmpty()){
            error =  "Documento es requerido"
        }
        mBinding.documentNumber.apply{
            isErrorEnabled = true
            error = error

        }

        return error == null

    }

    private fun validateDireccion(): Boolean {
        var error:String? = null
        val value: String = mBinding.addresstxt.text.toString()
        if (value.isEmpty()){
            error =  "Direccion es requerido"
        }
        if(error!=null){
            mBinding.address.apply{
                isErrorEnabled = true
                error = error
            }
        }
        return error == null
    }

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
     *  Funcion de Onclick
     */
    override fun onClick(view: View?) {
        if (view != null && view.id == R.id.btnRegistrarse){
            registrarUsuario(view)
            //navegarHaciaApp(SessionActivity::class.java)
        }
    }

    /**
     *  Funcion para registrar un usuario
     */
    private fun registrarUsuario(view: View?){
        val alertDialogBuilder = AlertDialog.Builder(this)
        val usuaRequest = UsuarioRequest(mBinding.documentTypeTex.text.toString()
            , mBinding.documentNumberTex.text.toString()
            , mBinding.fullNameTxt.text.toString()
            , mBinding.lastNametxt.text.toString()
            , mBinding.addresstxt.text.toString()
            , mBinding.emailtxt.text.toString()
            , mBinding.passwordtxt.text.toString()
            , mBinding.btnRegistrarAct.text.toString())
        val request = Api.build().crearUsuario(usuaRequest)

        request.enqueue(object:  retrofit2.Callback<UsuarioResponse> {
            override fun onResponse( call: Call<UsuarioResponse>,response: Response<UsuarioResponse>) {
                val usuarioResponse = response.body()
                if (usuarioResponse != null) {
                    messague = usuarioResponse.insertedId.toString()
                    alertDialogBuilder.setMessage("Usuario registrado Exitosamente: "+messague)
                    alertDialogBuilder.show()
                }
            }
            override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                println(t.message)
                messague = "El usuario no se creo correctamente"
                alertDialogBuilder.setMessage(messague)
                alertDialogBuilder.show()


            }

        })


    }

    /**
     *  Funcion OnFocusChange
     */
    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null){
            when(view.id){
                R.id.documentTypeTex ->{
                    if (hasFocus){
                        if (mBinding.documentType.isErrorEnabled){
                            mBinding.documentType.isErrorEnabled = false
                        }
                    }else{
                        validateDocumentType()
                    }

                }
                R.id.documentNumberTex ->{
                    if (hasFocus){
                        if (mBinding.documentNumber.isErrorEnabled){
                            mBinding.documentNumber.isErrorEnabled = false
                        }
                    }else{
                        validateDocumentNumber()
                    }
                }
                R.id.fullNameTxt -> {
                    if (hasFocus){
                        if (mBinding.fullName.isErrorEnabled){
                            mBinding.fullName.isErrorEnabled = false
                        }
                    }else{
                        validateFullName()
                    }
                }
                R.id.lastNametxt -> {
                    if (hasFocus){
                        if (mBinding.lastName.isErrorEnabled){
                            mBinding.lastName.isErrorEnabled = false
                        }
                    }else{
                        validateLastName()
                    }

                }
                R.id.addresstxt -> {
                    if (hasFocus){
                        if (mBinding.address.isErrorEnabled){
                            mBinding.address.isErrorEnabled = false
                        }
                    }else{
                        validateDireccion()
                    }
                }
                R.id.emailtxt -> {
                    if (hasFocus){
                        if (mBinding.email.isErrorEnabled){
                            mBinding.email.isErrorEnabled = false
                        }
                    }else{
                        validateEmail()
                    }

                }
                R.id.passwordtxt ->{
                    if (hasFocus){
                        if (mBinding.password.isErrorEnabled){
                            mBinding.password.isErrorEnabled = false
                        }
                    }else{
                        validatePassword()
                    }

                }
            }

        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }

    fun navegarHaciaApp(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}