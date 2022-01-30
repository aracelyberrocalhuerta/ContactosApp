package com.example.contactosapp.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Contact (
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("nombre")
    @Expose
    val nombre : String,
    @SerializedName("apellido")
    @Expose
    val apellido: String,
    @SerializedName("telefono")
    @Expose
    val telefono: Int,
    @SerializedName("correo")
    @Expose
    val correo: String,
    @SerializedName("ciudad")
    @Expose
    val ciudad: String,
    @SerializedName("favorito")
    @Expose
    val favorito: Int,
    @SerializedName("avatar")
    @Expose
    val avatar: String


)