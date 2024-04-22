package com.example.mycleanmarketapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycleanmarketapp.databinding.ActivityMainBinding
import com.example.mycleanmarketapp.databinding.ItemRowProductBinding as ItemRowProductBinding

class ListProductAdapter(private val listProduct: ArrayList<Product>) : RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowProductBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding){
                Glide.with(itemView.context).load(product.photo).apply(RequestOptions().override(55, 55)).into(imgItemPhoto)
                tvItemName.text = product.name
                tvItemDescription.text = product.description
                tvItemPrice.text= product.price
                }
            }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowProductBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listProduct[position])
    }
}