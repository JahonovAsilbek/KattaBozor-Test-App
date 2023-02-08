package com.example.kattabozor.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kattabozor.R
import com.example.kattabozor.databinding.ItemProductBinding
import com.example.kattabozor.entity.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val context: Context) : ListAdapter<Product, ProductAdapter.ProductVH>(MyDiffUtil()) {

    inner class ProductVH(var itemProductBinding: ItemProductBinding) : RecyclerView.ViewHolder(itemProductBinding.root) {
        fun onBind(product: Product) {
            itemProductBinding.apply {
                name.text = String.format(context.getString(R.string.product_name), product.name)
                brand.text = String.format(context.getString(R.string.product_brand), product.brand)
                category.text = String.format(context.getString(R.string.product_category), product.category)
                merchant.text = String.format(context.getString(R.string.product_merchant), product.merchant)
            }
            Picasso.get().load(product.image.url).into(itemProductBinding.image)

            val adapter = AttributeAdapter(list = product.attributes)
            itemProductBinding.rvAttr.adapter = adapter
        }

    }

    class MyDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        return ProductVH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        holder.onBind(getItem(position))
    }
}