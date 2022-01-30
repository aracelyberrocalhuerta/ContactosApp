package com.example.contactosapp.Network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import com.example.contactosapp.Model.Contact
import com.example.contactosapp.Model.contactRequest
import com.example.contactosapp.Model.contactRequestItem
import com.example.contactosapp.Network.Request.ContactRequest
import retrofit2.http.*

interface ContactService {
    @GET("contactos")
    fun getContacts(): Call<List<contactRequestItem>>

    @GET("{Id}")
    fun getContactbyId(@Path("Id") Id: Int,) : Call<Contact>

    @POST("0")
    fun createContact(@Body contact: ContactRequest) : Call<Any>
}