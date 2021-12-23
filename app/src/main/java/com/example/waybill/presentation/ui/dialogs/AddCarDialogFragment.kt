package com.example.waybill.presentation.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.waybill.R
import com.example.waybill.data.model.Car
import com.example.waybill.databinding.AddCarDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCarDialogFragment(): DialogFragment(R.layout.add_car_dialog) {

    val viewModel: AddCarDialogViewModel by viewModels()

    private var _binding: AddCarDialogBinding? = null
    private val binding get () = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddCarDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var carName = false
        var carMileage = false
        var consumptionSummer = false
        var consumptionWinter = false
        var fuelValue = false

        binding.apply {
            btAddCar.setOnClickListener {

                if (edCarName.text.isEmpty()) {
                    binding.edCarName.error = "Введите имя автомобиля"
                } else {
                    carName = true
                }
                if (edCarMileage.text.isEmpty()) {
                    binding.edCarMileage.error = "Введите пробег"
                } else {
                    carMileage = true
                }
                if (edConsumptionSummer.text.isEmpty()) {
                    binding.edConsumptionSummer.error = "Введите летний расход"
                } else {
                    consumptionSummer = true
                }
                if (edConsumptionWinter.text.isEmpty()) {
                    binding.edConsumptionWinter.error = "Введите зимний расход"
                } else {
                    consumptionWinter = true
                }
                if (edFuelValue.text.isEmpty()) {
                    binding.edFuelValue.error = "Введите остаток топлива"
                } else {
                    fuelValue = true
                }
                if (carName && carMileage && consumptionSummer && consumptionWinter && fuelValue) {
                    val car = Car(null, edCarName.text.toString(), edCarMileage.text.toString(),
                        edConsumptionSummer.text.toString(), edConsumptionWinter.text.toString(),
                        edFuelValue.text.toString())
                    viewModel.createCar(car)
                    dismiss()
                } else {
                    return@setOnClickListener
                }
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



