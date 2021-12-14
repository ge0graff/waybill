package com.example.waybill.presentation.ui.recyclerviews.cars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.data.model.Car
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.CarItemBinding

class CarsAdapter(): ListAdapter<Car, CarsAdapter.CarsViewHolder>(DiffCallback()) {

//    var carActionListener: CarActionListener? = null
//    var clickHandler: CarForwardClick = context as CarForwardClick


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val binding = CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarsAdapter.CarsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CarsViewHolder(private val binding: CarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(car: Car) = with(binding) {
            carItemCarName.text = car.name
            carItemMileage.text = car.mileage + " км"

//            rcInfoButton.setOnClickListener {
//                clickHandler?.carForwardClick(car)
//            }

            itemView.setOnClickListener {
                SelectedCar.apply {
                    id = car.id ?: 0
                    name = car.name
                    mileage = car.mileage
                    consumption_summer = car.consumption_summer
                    consumption_winter = car.consumption_winter
                    fuel_value = car.fuel_value
                }
                notifyDataSetChanged()
            }

            if (car.id == SelectedCar.id){
                rcDoneButton.visibility = View.VISIBLE
            } else{
                rcDoneButton.visibility = View.GONE
            }
        }

        }

    class DiffCallback: DiffUtil.ItemCallback<Car>(){
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }

    }
}