package com.example.waybill.presentation.ui.dialogs

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = arguments?.getInt("token") ?: 0
        val id = arguments?.getInt("car_id")

        addEditCar(id, token)
    }

    private fun addEditCar(id: Int?, token: Int) {

        val token = token
        val id = id
        val name = arguments?.getString("name")
        val mileage = arguments?.getString("mileage")
        val consumptionSummer= arguments?.getString("cSum")
        val consumptionWinter= arguments?.getString("cWin")
        val fuelValue = arguments?.getString("fuel")
        val buttonText = arguments?.getString("button_text")
        val editText = arguments?.getString("edit_text")

        if (token == 1) {
        binding.apply {
            edCarName.setText(name)
            edCarMileage.setText(mileage)
            edConsumptionSummer.setText(consumptionSummer)
            edConsumptionWinter.setText(consumptionWinter)
            edFuelValue.setText(fuelValue)
            btAddCar.text = buttonText
            addCarDialogTitle.text = editText
            }
        }

        var carName = false
        var carMileage = false
        var carConsumptionSummer = false
        var carConsumptionWinter = false
        var carFuelValue = false

        binding.apply {
            btAddCar.setOnClickListener {

                if (edCarName.editableText.isEmpty()) {
                    binding.edCarName.error = resources.getString(R.string.add_car_dialog_name_error)
                } else {
                    carName = true
                }
                if (edCarMileage.editableText.isEmpty()) {
                    binding.edCarMileage.error = resources.getString(R.string.add_car_dialog_mileage_error)
                } else {
                    carMileage = true
                }
                if (edConsumptionSummer.editableText.isEmpty()) {
                    binding.edConsumptionSummer.error = resources.getString(R.string.add_car_dialog_consumption_summer_error)
                } else {
                    carConsumptionSummer = true
                }
                if (edConsumptionWinter.editableText.isEmpty()) {
                    binding.edConsumptionWinter.error = resources.getString(R.string.add_car_dialog_consumption_winter_error)
                } else {
                    carConsumptionWinter = true
                }
                if (edFuelValue.editableText.isEmpty()) {
                    binding.edFuelValue.error = resources.getString(R.string.add_car_dialog_fuel_error)
                } else {
                    carFuelValue = true
                }
                if (carName && carMileage && carConsumptionSummer && carConsumptionWinter && carFuelValue) {
                    val car = Car(id, edCarName.text.toString(), edCarMileage.text.toString(),
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



