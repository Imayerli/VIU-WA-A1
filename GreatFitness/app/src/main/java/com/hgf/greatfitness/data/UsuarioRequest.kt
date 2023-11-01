package com.hgf.greatfitness.data

data class UsuarioRequest(
    var doc_type:String,
    val doc_number:String,
    val name:String,
    val last_name:String,
    val address:String,
    val phone:String,
    val email:String,
    val password:String
)

