package com.example.kattabozor.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kattabozor.databinding.ItemAttributeBinding
import com.example.kattabozor.entity.Attribute

class AttributeAdapter(var list: List<Attribute>) : RecyclerView.Adapter<AttributeAdapter.AttributeVH>() {

    inner class AttributeVH(var itemAttributeBinding: ItemAttributeBinding) : RecyclerView.ViewHolder(itemAttributeBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributeVH {
        return AttributeVH(ItemAttributeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AttributeVH, position: Int) {
        val attribute = list[position]
        holder.itemAttributeBinding.tv.text = "${attribute.name} - ${attribute.value}"
    }
}