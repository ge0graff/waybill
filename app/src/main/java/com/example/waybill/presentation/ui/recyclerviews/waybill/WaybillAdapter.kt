package com.example.waybill.presentation.ui.recyclerviews.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.data.model.Waybills
import com.example.waybill.data.objects.DataObject
import com.example.waybill.databinding.WaybillItemBinding

class WaybillAdapter(): ListAdapter<Waybills, WaybillAdapter.WaybillHolder>(DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaybillHolder {
        val binding = WaybillItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WaybillHolder(binding)
    }

    override fun onBindViewHolder(holder: WaybillHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class WaybillHolder(private val binding: WaybillItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(waybill: Waybills) = with(binding){
            wbRcvMileage.text = waybill.mileage + " км."
            wbRcvDalyMileage.text = waybill.dailyMileage + " км."
            wbRcvFuelValue.text = waybill.fuelValue + " л."
            wbRcvRefuling.text = waybill.refueling + " л."
            wbRcvData.text = DataObject.dateFormat + " г."
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Waybills>() {
        override fun areItemsTheSame(oldItem: Waybills, newItem:Waybills): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Waybills, newItem: Waybills): Boolean {
            return oldItem == newItem
        }
    }
}