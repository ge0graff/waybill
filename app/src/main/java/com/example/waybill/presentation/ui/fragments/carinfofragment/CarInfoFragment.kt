package com.example.waybill.presentation.ui.fragments.carinfofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.waybill.R
import com.example.waybill.databinding.FragmentCarInfoBinding


class CarInfoFragment : Fragment() {

    private var _binding: FragmentCarInfoBinding? = null
    private val binding get () = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        putText()

    }

    private fun putText(){

        val id = arguments?.getInt("car_id")
        val name = arguments?.getString("name")
        val mileage = arguments?.getString("mileage")
        val consumptionSummer= arguments?.getString("cSum")
        val consumptionWinter= arguments?.getString("cWin")
        val fuelValue = arguments?.getString("fuel")

        binding.apply {
            carInfoCarNameText.text = resources.getString(R.string.info_fragment_car_name, name)
            carInfoCarMileage.text = resources.getString(R.string.car_mileage, mileage)
            carInfoConsumptionSummer.text = resources.getString(R.string.car_consumption_summer, consumptionSummer)
            carInfoConsumptionWinter.text = resources.getString(R.string.car_consumption_winter, consumptionWinter)
            carInfoFuelValue.text = resources.getString(R.string.car_fuel_value, fuelValue)
        }

//        editCar(id!!, name, mileage, consumptionSummer, consumptionWinter, fuelValue)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



//private fun editCar(
//    id: Int,
//    name: String?,
//    mileage: String?,
//    consumptionSummer: String?,
//    consumptionWinter: String?,
//    fuelValue: String?) {
//
//    val bundle = Bundle()
//    bundle.putInt("car_id", id)
//    bundle.putInt("token", 1)
//    bundle.putString("button_text", "Сохранить")
//    bundle.putString("edit_text", "Редактирование")
//    bundle.putString("name", name)
//    bundle.putString("mileage", mileage)
//    bundle.putString("cSum", consumptionSummer)
//    bundle.putString("cWin", consumptionWinter)
//    bundle.putString("fuel", fuelValue)
//
//    binding.carEditButton.setOnClickListener {
//        val dialog = AddCarDialogFragment()
//        dialog.arguments = bundle
//        dialog.show(parentFragmentManager, "customDialog")
//    }
//}

