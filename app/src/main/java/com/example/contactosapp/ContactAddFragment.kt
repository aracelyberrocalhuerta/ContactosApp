package com.example.contactosapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.fragment.app.Fragment
import com.example.contactosapp.Model.Favorito
import com.example.contactosapp.Network.ContactApi
import com.example.contactosapp.Network.Request.ContactRequest
import com.example.contactosapp.databinding.FragmentContactAddBinding

class ContactAddFragment : Fragment(){
    private var _binding: FragmentContactAddBinding? = null
    private val binding
        get()  = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener{
            if (validateField()){
                sendContactToServer()
            }
        }
    }

    private fun validateField(): Boolean {
        val nombre = binding.etNombre.text.toString()
        if (nombre.isEmpty()){
            showError("Nombre está vacio")
            return false
        }
        val apellido = binding.etApellido.text.toString()
        if (apellido.isEmpty()){
            showError("Apellido está vacio")
            return false
        }
        val telefono = binding.etTelefono.text.toString()
        if (telefono.isEmpty()){
            showError("Nombre está vacio")
            return false
        }
        val correo = binding.etCorreo.text.toString()
        if (correo.isEmpty()){
            showError("Nombre está vacio")
            return false
        }
        val ciudad = binding.etCiudad.text.toString()
        if (correo.isEmpty()){
            showError("Nombre está vacio")
            return false
        }
        val avatar = binding.etAvatar.text.toString()
        if (avatar.isEmpty()){
            showError("Nombre está vacio")
            return false
        }

        return true
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun sendContactToServer() {
        val nombre = binding.etNombre.text.toString()
        val apellido = binding.etApellido.text.toString()
        val telefono = Integer.parseInt(binding.etTelefono.text.toString())
        val correo = binding.etCorreo.text.toString()
        val ciudad = binding.etCiudad.text.toString()
        val favorito = 0
        val avatar = binding.etAvatar.text.toString()

        val contactRequest = ContactRequest(nombre,apellido,telefono,correo,ciudad,
            Favorito(listOf(1),"Buffer"),avatar)

        ContactApi.service.createContact(contactRequest).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                    resetScreen()
                } else {
                    showError("Error en la respuesta")
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("requestData", "error", t)
                showError("Error en la conexión")
            }
        })
    }
    private fun resetScreen() {
        binding.etNombre.text = null
        binding.etApellido.text = null
        binding.etTelefono.text = null
        binding.etCorreo.text = null
        binding.etCiudad.text = null
        binding.etAvatar.text = null

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}