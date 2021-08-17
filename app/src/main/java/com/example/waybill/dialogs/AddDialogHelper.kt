package com.example.waybill.dialogs

import android.app.AlertDialog
import com.example.waybill.MyCarActivity
import com.example.waybill.cars.Car
import com.example.waybill.cars.CarsAdapter
import com.example.waybill.databinding.AddCarDialogBinding

class AddDialogHelper(val act: MyCarActivity, adapter: CarsAdapter) {
    private val activity = act
    val adp = adapter


    fun createAddDialog(){
    val builder = AlertDialog.Builder(activity)
        val dialogBuilder = AddCarDialogBinding.inflate(activity.layoutInflater)
        builder.setView(dialogBuilder.root)
        val dialog = builder.create()

        dialogBuilder.apply {
           btAddCar.setOnClickListener {
                val car = Car(edCarName.text.toString(), edCarMileage.text.toString()+".км",
                    edConsumptionSummer.text.toString()+".л", edConsumptionWinter.text.toString()+".л",
                    edFuelValue.text.toString()+".л")
                adp.addCar(car)
                dialog.dismiss()
            }

        }

        dialog.show()

    }
}