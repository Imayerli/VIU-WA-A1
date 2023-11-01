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
    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val clientBuilder = OkHttpClient.Builder().addInterceptor(interceptor).build()
    private val buider: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://poetic-starlight-7b9552.netlify.app/.netlify/functions/")
        .client(clientBuilder)
        .addConverterFactory(GsonConverterFactory.create())

    interface  RemoteService{
        @GET("general")
        fun obtenerGeneralidad(@Query("type") limit:String): Call<GeneralidadResponse>


        @POST("users")
        fun crearUsuario(@Body body: UsuarioRequest): Call<UsuarioResponse>

        @POST("login")
        fun iniciarSesion(@Body body: InicioSesionRequest): Call<InicioSesionResponse>
    }

    fun build(): RemoteService{
        return  buider.build().create(RemoteService::class.java)
    }


}