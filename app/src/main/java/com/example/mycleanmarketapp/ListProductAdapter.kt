package com.example.mycleanmarketapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycleanmarketapp.model.product
import com.example.mycleanmarketapp.databinding.ItemRowProductBinding as ItemRowProductBinding

class ListProductAdapter(private val listProduct: ArrayList<product>) : RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {

    var onItemClick: ((product) -> Unit)? = null
    inner class ListViewHolder(private val binding: ItemRowProductBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: product) {
            with(binding){
                Glide.with(itemView.context).load(product.photo).apply(RequestOptions().override(100, 100)).into(imgItemPhoto)
                tvItemName.text = product.ProductName
                tvItemDescription.text = product.description
                tvItemPrice.text =  "" + product.price

                }
            }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowProductBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listProduct[position])

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listProduct[position])
        }
    }



    override fun getItemCount(): Int = listProduct.size




}