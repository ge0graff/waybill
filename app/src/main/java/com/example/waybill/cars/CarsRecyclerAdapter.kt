  package com.example.waybill.cars


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.MainActivity
import com.example.waybill.R
import com.example.waybill.data.Cars
import com.example.waybill.data.carselect.SelectedCar
import com.example.waybill.databinding.CarItemBinding


  class CarsRecyclerAdapter(val context: Context, var carList: List<Cars>):
    RecyclerView.Adapter<CarsRecyclerAdapter.CarsHolder>() {
    val dataBase = MainActivity.getInstance(context)
    val pref = context.getSharedPreferences("Car", 0)
    val editPref = pref?.edit()

    inner class CarsHolder(item: View): RecyclerView.ViewHolder(item) {
        var binding = CarItemBinding.bind(item)
        private var clickHandler: ClickEventHandler = context as ClickEventHandler

        fun bind(car: Cars) = with(binding){
            carItemCarName.text = car.name
            carItemMileage.text = car.mileage + " км"

               rcInfoButton.setOnClickListener {
                    clickHandler.forwardClick(car)
                }

            itemView.setOnClickListener {
                editPref?.putInt("prefId", car.id!!)
                editPref?.putString("prefNM", car.name)
                editPref?.putString("prefML", car.mileage)
                editPref?.putString("prefCS", car.consumption_summer)
                editPref?.putString("prefCW", car.consumption_winter)
                editPref?.putString("prefFV", car.fuel_value)
                editPref?.apply()
                notifyDataSetChanged()
            }

            SelectedCar.id = pref.getInt("prefId", -1)
            SelectedCar.name = pref.getString("prefNM", "").toString()
            SelectedCar.mileage = pref.getString("prefML", "").toString()
            SelectedCar.consumption_summer = pref.getString("prefCS", "").toString()
            SelectedCar.consumption_winter = pref.getString("prefCW", "").toString()
            SelectedCar.fuel_value = pref.getString("prefFV", "").toString()

            if (car.id == SelectedCar.id){
               rcDoneButton.visibility = View.VISIBLE
            }
                else{
                    rcDoneButton.visibility = View.GONE
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
        dataBase.carsDao().delete(item)
    }

    interface ClickEventHandler {
        fun forwardClick(car: Cars)
            }
}