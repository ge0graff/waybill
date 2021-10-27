package com.example.waybill.dialogs


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.waybill.R
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.managers.CarsDatabaseManagerHolder
import com.example.waybill.data.model.Car
import kotlinx.android.synthetic.main.add_car_dialog.*
import kotlinx.android.synthetic.main.add_car_dialog.view.*


class AddCarDialogFragment(val adapter: CarsRecyclerAdapter): DialogFragment() {

    private val databaseManager = CarsDatabaseManagerHolder.carsDatabaseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.add_car_dialog, container, false)
        rootView.bt_add_car.setOnClickListener {

            val car = Car(null, ed_car_name.text.toString(), ed_car_mileage.text.toString(),
                ed_consumption_summer.text.toString(), ed_fuel_value.text.toString(),
                ed_fuel_value.text.toString())
            databaseManager.insertCar(car)
            adapter.addCar(car)
            dismiss()
        }


        return rootView
    }


}