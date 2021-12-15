package com.example.waybill.presentation.ui.dialogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.waybill.R
import com.example.waybill.databinding.AddCarDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCarDialogFragment(): DialogFragment(R.layout.add_car_dialog) {

    val viewModel: AddCarDialogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = AddCarDialogBinding.bind(view)

        val edCarName = binding.edCarName.text
        val edCarMileage = binding.edCarMileage.text
        val edConsumptionSummer = binding.edConsumptionSummer.text
        val edConsumptionWinter = binding.edConsumptionWinter.text
        val edFuelValue = binding.edFuelValue.text


        fun onVisible (view: View){
            view.visibility = View.VISIBLE
        }
        fun onInvisible (view: View){
            view.visibility = View.INVISIBLE
        }

        binding.apply {
            btAddCar.setOnClickListener {
                if(edCarName.isBlank()) {
                onVisible(imCarNameError)
                }else{
                    onInvisible(imCarNameError)
                }
                if(edCarMileage.isBlank()) {
                    onVisible(imCarMileageError)
                }else{
                    onInvisible(imCarMileageError)
                }
                if(edConsumptionSummer.isBlank()) {
                    onVisible(imCarCsError)
                }else{
                    onInvisible(imCarCsError)
                }
                if(edConsumptionWinter.isBlank()) {
                    onVisible(imCarCwError)
                }else{
                    onInvisible(imCarCwError)
                }
                if(edFuelValue.isBlank()) {
                    onVisible(imFuelValueError)
                }else{
                    onInvisible(imFuelValueError)
                }

                viewModel.onSaveClick()
            }

//            edCarName.addTextChangedListener {
//                viewModel.carName = it.toString()
//            }
//            edCarMileage.addTextChangedListener {
//                viewModel.carMileage = it.toString()
//            }
//            edConsumptionSummer.addTextChangedListener {
//                viewModel.consumptionSummer = it.toString()
//            }
//            edConsumptionWinter.addTextChangedListener {
//                viewModel.consumptionWinter = it.toString()
//            }
//            edFuelValue.addTextChangedListener {
//                viewModel.fuelValue = it.toString()
//            }
        }



//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewModel.addEditCarEvent.collect { event ->
//                when (event) {
//                    is AddCarDialogViewModel.AddEditCarEvent.ShowInvalidInputCarNameMessage -> {
//                        if(event.boolean){
//                            binding.imCarNameError.visibility = View.VISIBLE
//                        }
//                        if(!event.boolean){
//                            binding.imCarNameError.visibility = View.INVISIBLE
//                        }
//                    }
//                    is AddCarDialogViewModel.AddEditCarEvent.ShowInvalidInputCarMileageMessage -> {
//                        binding.edCarMileage.setText(event.msg)
//                    }
//                    is AddCarDialogViewModel.AddEditCarEvent.ShowInvalidInputConsumptionSummerMessage -> {
//                        binding.edConsumptionSummer.setText(event.msg)
//                    }
//                    is AddCarDialogViewModel.AddEditCarEvent.ShowInvalidInputConsumptionWinterMessage -> {
//                        binding.edConsumptionWinter.setText(event.msg)
//                    }
//                    is AddCarDialogViewModel.AddEditCarEvent.ShowInvalidInputFuelValueMessage -> {
//                        binding.edFuelValue.setText(event.msg)
//                    }
//                    is AddCarDialogViewModel.AddEditCarEvent.DismissDialog -> {
//                        Snackbar.make(requireView(), "Автомобиль добавлен", Snackbar.LENGTH_LONG).show()
//                        dismiss()
//                    }
//                }.exhaustive
//            }
//        }
    }
}

//        val car = Car(null, ed_car_name.text.toString(), ed_car_mileage.text.toString(),
//            ed_consumption_summer.text.toString(), ed_consumption_winter.text.toString(),
//            ed_fuel_value.text.toString())
