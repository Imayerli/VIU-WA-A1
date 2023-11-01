package com.hgf.greatfitness.data

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

object Api {
    val clientBuilder = OkHttpClient.Builder()
    val client: OkHttpClient = clientBuilder.build()
    private val buider: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://localhost:8080")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())

    interface  RemoteService{
        @GET("/general")
        fun obtenerGeneralidad(@Query("type") limit:String): Call<GeneralidadResponse>


        @POST("/users")
        fun crearUsuario(@Body body: UsuarioRequest): Call<UsuarioResponse>

        @POST("/login")
        fun iniciarSesion(@Body body: InicioSesionRequest): Call<InicioSesionResponse>
    }

    fun build(): RemoteService{
        return  buider.build().create(RemoteService::class.java)
    }


}