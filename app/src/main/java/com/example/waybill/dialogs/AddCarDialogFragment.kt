package com.example.waybill.dialogs


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.room.Room
import com.example.waybill.MainActivity
import com.example.waybill.R
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.Cars
import com.example.waybill.data.CarsDatabase
import kotlinx.android.synthetic.main.add_car_dialog.*
import kotlinx.android.synthetic.main.add_car_dialog.view.*


class AddCarDialogFragment(val adapter: CarsRecyclerAdapter): DialogFragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.add_car_dialog, container, false)
        val db = MainActivity.getInstance(requireContext())
        rootView.bt_add_car.setOnClickListener {

            val car = Cars(1, ed_car_name.text.toString(), ed_car_mileage.text.toString(), ed_consumption_summer.text.toString(),
                ed_consumption_winter.text.toString(), ed_fuel_value.text.toString()+".л")
            db.carsDao().insert(car)
            adapter.addCar(car)
            dismiss()
            }


        return rootView
    }


}