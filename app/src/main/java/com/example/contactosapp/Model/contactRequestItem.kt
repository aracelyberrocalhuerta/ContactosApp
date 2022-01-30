package com.example.contactosapp.Model


import com.google.gson.annotations.SerializedName

data class contactRequestItem(
    @SerializedName("apellido")
    val apellido: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("ciudad")
    val ciudad: String,
    @SerializedName("correo")
    val correo: String,
    @SerializedName("favorito")
    val favorito: Favorito,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("telefono")
    val telefono: Int
)