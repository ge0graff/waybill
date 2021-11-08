package com.example.waybill.presentation.ui.recyclerviews.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.R
import com.example.waybill.data.model.Waybills
import com.example.waybill.data.objects.DataObject
import com.example.waybill.databinding.WaybillItemBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class WaybillRecyclerAdapter(private var waybill: List<Waybills>): RecyclerView.Adapter<WaybillRecyclerAdapter.WaybillHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaybillHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.waybill_item, parent, false)
        return WaybillHolder(view)
    }

    override fun onBindViewHolder(holder: WaybillHolder, position: Int) {
        holder.bind(waybill[position])

    }

    override fun getItemCount(): Int {
        return waybill.size
    }

    inner class WaybillHolder(item: View): RecyclerView.ViewHolder(item) {
        private var binding = WaybillItemBinding.bind(item)

        fun bind(waybill: Waybills) = with(binding){
            wbRcvMileage.text = waybill.mileage
            wbRcvDalyMileage.text = waybill.dailyMileage
            wbRcvFuelValue.text = waybill.fuelValue
            wbRcvRefuling.text = waybill.refueling
            wbRcvData.text = DataObject.dateFormat


        }

    }


}