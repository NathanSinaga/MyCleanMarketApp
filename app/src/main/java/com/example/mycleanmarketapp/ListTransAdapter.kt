package com.example.mycleanmarketapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanmarketapp.databinding.ItemTransRowBinding
import com.example.mycleanmarketapp.model.Transaction

class ListTransAdapter(private val listTrans: ArrayList<Transaction>) : RecyclerView.Adapter<ListTransAdapter.ListViewHolder>() {

    var onItemClick: ((Transaction) -> Unit)? = null

    inner class ListViewHolder(private val binding: ItemTransRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            with(binding){
                transId.text= transaction.TransId
                transDate.text = transaction.TransDate
                transTotal.text = transaction.TransTotal.toString()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTransRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listTrans.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listTrans[position])

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listTrans[position])
        }
    }
}