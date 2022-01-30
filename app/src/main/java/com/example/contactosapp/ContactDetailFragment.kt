package com.example.contactosapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactosapp.Extensions.imageUrl
import com.example.contactosapp.Model.Contact
import com.example.contactosapp.Network.ContactApi
import com.example.contactosapp.databinding.FragmentContactDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KProperty

class ContactDetailFragment : Fragment(){
    private val args: ContactDetailFragmentArgs by navArgs()
    private var _binding: FragmentContactDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.contactID?.let {
            requestData(it)
        } ?: showError("Id Null")


    }

    private fun requestData(contactID: Int) {
        ContactApi.service.getContactbyId(contactID).enqueue(object : Callback<Contact> {
            override fun onResponse(call: Call<Contact>, response: Response<Contact>) {
                if (response.isSuccessful) {
                    populateUi(response.body())
                } else {
                    showError("Error en la respuesta")
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<Contact>, t: Throwable) {
                Log.e("requestData", "error", t)
                showError("Error en la conexión")
            }
        })

    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    private fun populateUi(contact: Contact?) {
        contact?.let {
            val nombreCompleto = it.nombre + it.apellido
            binding.tvNombreCompleto.text = nombreCompleto
            binding.tvTelefono.text = it.telefono.toString()
            binding.tvCorreo.text= it.correo
            binding.tvCiudad.text = it.ciudad
            binding.ivAvatar.imageUrl(it.avatar)

        } ?: showError("Error to retrieve technology")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

