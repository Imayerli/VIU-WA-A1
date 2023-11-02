package com.hgf.greatfitness.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

object Api {
    /**
     * Se inician variables para los logs y cliente
     */
    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val clientBuilder = OkHttpClient.Builder().addInterceptor(interceptor).build()
    private val buider: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://poetic-starlight-7b9552.netlify.app/.netlify/functions/")
        .client(clientBuilder)
        .addConverterFactory(GsonConverterFactory.create())

    /**
     *  Se realiza interfaz pra acceder a cada operacion
     */
    interface  RemoteService{

        /**
         * Funcion encargada de consultar las generalidades
         */
        @GET("general")
        fun obtenerGeneralidad(@Query("type") limit:String): Call<List<GeneralidadResponse>>


        /**
         * Funcion encargada de de crear el usuario
         */
        @POST("user")
        fun crearUsuario(@Body body: UsuarioRequest): Call<UsuarioResponse>

        /**
         * Funcion encargada de realizadr el inicio de sesion
         */
        @POST("login")
        fun iniciarSesion(@Body body: InicioSesionRequest): Call<InicioSesionResponse>
    }

    /**
     * guncion remoteService
     */
    fun build(): RemoteService{
        return  buider.build().create(RemoteService::class.java)
    }


}