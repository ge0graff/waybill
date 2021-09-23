package com.example.waybill.cars


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.waybill.MainActivity
import com.example.waybill.R
import com.example.waybill.data.Cars
import com.example.waybill.data.CarsDao
import com.example.waybill.databinding.CarItemBinding

class CarsRecyclerAdapter(context: Context, var carList: List<Cars>):
    RecyclerView.Adapter<CarsRecyclerAdapter.CarsHolder>() {
    val db = MainActivity.getInstance(context)





    class CarsHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = CarItemBinding.bind(item)

        fun bind(car: Cars) = with(binding){
            carItemCarName.text = car.name
            carItemMileage.text = car.mileage
//            carItemConsumptionSummerValue.text = car.consumption_summer
//            carItemConsumptionWinterValue.text = car.consumption_winter
//            carItemFuelValue.text = car.fuel_value
        }
    }

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

    fun addCar(car: Cars){
        (carList as MutableList).add(car)
        notifyDataSetChanged()

    }

    fun removeItem(position: Int){
        val item = carList[position]
        (carList as MutableList).remove(item)
        notifyItemChanged(position)
        db.carsDao().delete(item)

    }




}