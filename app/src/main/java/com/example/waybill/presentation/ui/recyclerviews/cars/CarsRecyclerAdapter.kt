package com.example.waybill.presentation.ui.recyclerviews.cars


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.R
import com.example.waybill.data.model.Car
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.CarItemBinding

  class CarsRecyclerAdapter(context: Context, private var carList: List<Car>):
      RecyclerView.Adapter<CarsRecyclerAdapter.CarsHolder>(){

    var carActionListener: CarActionListener? = null
    var clickHandler: CarForwardClick = context as CarForwardClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarsHolder(view)
    }

    override fun onBindViewHolder(holder: CarsHolder, position: Int) {
        holder.bind(carList[position])
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun addCar(car: Car){
        (carList as MutableList).add(car)
        notifyItemInserted(carList.size - 1)
    }

    fun removeItem(position: Int){
        val item = carList[position]
        (carList as MutableList).remove(item)
        notifyItemRemoved(position)
        carActionListener?.removeCar(item)
    }

    inner class CarsHolder(item: View): RecyclerView.ViewHolder(item) {
        private var binding = CarItemBinding.bind(item)

        fun bind(car: Car) = with(binding) {
            carItemCarName.text = car.name
            carItemMileage.text = car.mileage + " км"

            rcInfoButton.setOnClickListener {
                clickHandler.carForwardClick(car)
            }

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


}