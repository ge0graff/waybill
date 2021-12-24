package com.example.waybill.presentation.ui.recyclerviews.cars

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.data.model.Car
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.CarItemBinding

class CarsAdapter(
    private val actionListener: CarForwardClick
    ): ListAdapter<Car, CarsAdapter.CarsViewHolder>(DiffCallback()) {

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

        @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
        fun bind(car: Car) = with(binding) {
            carItemCarName.text = car.name
            carItemMileage.text = car.mileage + " км"

            rcInfoButton.setOnClickListener {
                actionListener.onCarDetails(car)
            }

            itemView.setOnClickListener {
                actionListener.onCarSelect(car)
                notifyDataSetChanged()
            }

            if (car.id == SelectedCar.id) {
                rcDoneButton.visibility = View.VISIBLE
            } else {
                rcDoneButton.visibility = View.GONE
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }

    }
}