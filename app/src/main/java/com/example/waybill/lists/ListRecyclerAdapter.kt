package com.example.waybill.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.R
import com.example.waybill.databinding.ListItemBinding

class ListRecyclerAdapter(private val lists: ArrayList<Mouths>): RecyclerView.Adapter<ListRecyclerAdapter.ListHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(lists[position])

    }

    override fun getItemCount(): Int {
       return lists.size
    }

    inner class ListHolder(item: View): RecyclerView.ViewHolder(item) {
        private var binding = ListItemBinding.bind(item)
        fun bind(list: Mouths){
            binding.mtName.text = list.mouths

        }

    }


}