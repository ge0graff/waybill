package com.example.waybill.cars


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.MainActivity
import com.example.waybill.R
import com.example.waybill.data.Cars
import com.example.waybill.databinding.CarItemBinding
import com.example.waybill.fragments.CarInfoFragment


class CarsRecyclerAdapter(val context: Context, var carList: List<Cars>):
    RecyclerView.Adapter<CarsRecyclerAdapter.CarsHolder>() {
    val db = MainActivity.getInstance(context)
    private var clickHandler: ClickEventHandler = context as ClickEventHandler


    inner class CarsHolder(item: View): RecyclerView.ViewHolder(item) {
        var binding = CarItemBinding.bind(item)


        fun bind(car: Cars) = with(binding){
            carItemCarName.text = car.name
            carItemMileage.text = car.mileage + " км"

            itemView.setOnClickListener {

//                Я знаю, это хуйня

                carName = car.name
                mileage = car.mileage
                consumption_summer = car.consumption_summer
                consumption_winter = car.consumption_winter
                fuel_value = car.fuel_value
                clickHandler.forwardClick(CarsHolder(it))

//          Этот код я пытался воплотить в жизь
//                val bundle = Bundle()
//                bundle.putString("MyArg", "тест")
//                val carInfoFragment = CarInfoFragment()
//                carInfoFragment.arguments = bundle
            }
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

    interface ClickEventHandler {
        fun forwardClick(holder: CarsRecyclerAdapter.CarsHolder)
            }

    companion object{
        var carName = ""
        var mileage = ""
        var consumption_summer = ""
        var consumption_winter = ""
        var fuel_value = ""
    }
}