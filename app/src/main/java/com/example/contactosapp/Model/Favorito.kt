package com.example.contactosapp.Model


import com.google.gson.annotations.SerializedName

data class Favorito(
    @SerializedName("data")
    val `data`: List<Int>,
    @SerializedName("type")
    val type: String
)