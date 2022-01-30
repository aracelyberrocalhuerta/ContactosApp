package com.example.contactosapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactosapp.Extensions.imageUrl
import com.example.contactosapp.Model.Contact
import com.example.contactosapp.Model.contactRequestItem
import com.example.contactosapp.databinding.ItemContactBinding

class ContactAdapter (private val onContactClicked: (contactRequestItem)-> Unit):
    ListAdapter<contactRequestItem, ContactAdapter.ViewHolder>(ContactItemCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {
        val contact : contactRequestItem = getItem(position)
        val nombreCompleto = contact.nombre + contact.apellido
        holder.binding.tvNombreCompleto.text = nombreCompleto
        holder.binding.ivAvatar.imageUrl(contact.avatar)
        holder.binding.root.setOnClickListener{
            onContactClicked(contact)
        }
    }


    inner class ViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root)


}
class ContactItemCallback : DiffUtil.ItemCallback<contactRequestItem>() {
    override fun areItemsTheSame(oldItem: contactRequestItem, newItem: contactRequestItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: contactRequestItem, newItem: contactRequestItem): Boolean =
        oldItem.id == newItem.id
}