package com.example.mycleanmarketapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycleanmarketapp.model.Product
import com.example.mycleanmarketapp.databinding.ItemCartRowBinding as ItemRowCartBinding

class ListRowCartAdapter(private val listProduct: ArrayList<Product>) : RecyclerView.Adapter<ListRowCartAdapter.ListViewHolder>() {

    var onItemClick: ((Product) -> Unit)? = null
    inner class ListViewHolder(private val binding: ItemRowCartBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding){
                Glide.with(itemView.context).load(product.ProductPhoto).apply(RequestOptions().override(100, 100)).into(imgItemPhoto)
                tvItemName.text = product.ProductName
                tvItemPrice.text =  "" + product.ProductPrice

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowCartBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listProduct[position])

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listProduct[position])
        }
    }


}