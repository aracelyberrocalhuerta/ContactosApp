package com.example.contactosapp.Network.Request

import com.example.contactosapp.Model.Favorito
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContactRequest (
    @SerializedName("nombre")
    @Expose
    val nombre: String,
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
    val favorito: Favorito,
    @SerializedName("avatar")
    @Expose
    val avatar: String = "https://miro.medium.com/max/720/1*W35QUSvGpcLuxPo3SRTH4w.png"
    )
