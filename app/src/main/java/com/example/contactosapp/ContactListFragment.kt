package com.example.contactosapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactosapp.Model.Contact
import com.example.contactosapp.Model.contactRequest
import com.example.contactosapp.Model.contactRequestItem
import com.example.contactosapp.Network.ContactApi
import com.example.contactosapp.databinding.FragmentContactListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactListFragment : Fragment(){
    private var _binding: FragmentContactListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = ContactAdapter {
        val action =
            ContactListFragmentDirections
                .actionContactListFragmentToContactDetailFragment(it.id)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configUi()
        requestData()

    }

    private fun requestData() {
        ContactApi.service.getContacts().enqueue(object : Callback<List<contactRequestItem>> {
            override fun onResponse(
                call: Call<List<contactRequestItem>>,
                response: Response<List<contactRequestItem>>
            ) {
                if (response.isSuccessful) {
                    val lista : MutableList<contactRequestItem> = mutableListOf()
                    response.body()?.let{lista.addAll(it)}
                    adapter.submitList(lista)

                } else {
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<List<contactRequestItem>>, t: Throwable) {
                Toast.makeText(context, "Error en la conexión", Toast.LENGTH_SHORT).show()
                Log.e("requestData", "error", t)
            }
        })
    }

    private fun configUi() {
        binding.btnAdd.setOnClickListener {
            val action =
                ContactListFragmentDirections.actionContactListFragmentToContactAddFragment()
            findNavController().navigate(action)
        }
        binding.rvContact.layoutManager = GridLayoutManager(context, 2)
        binding.rvContact.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}