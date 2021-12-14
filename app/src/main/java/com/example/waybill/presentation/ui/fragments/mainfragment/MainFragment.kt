package com.example.waybill.presentation.ui.fragments.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.FragmentMainBinding


class MainFragment() : Fragment(){

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var binding: FragmentMainBinding
    private var currentMileage = ""
    private var refuelValue = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, MainFragmentViewModelFactory())
            .get(MainFragmentViewModel::class.java)
        binding.mfCarName.text = SelectedCar.name

        viewModel.dalyMileageValueLive.observe(viewLifecycleOwner, Observer {
            binding.mfCarDailyMileageValue.text = it
        })

        viewModel.fuelValueLive.observe(viewLifecycleOwner, Observer {
            binding.mfFuelValue.text = it
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mfCalculate.setOnClickListener {
            //Клик по кнопке расчитать
            currentMileage = binding.mfMileageValue.text.toString()
            refuelValue = binding.mfRefuelValue.text.toString()
            if(currentMileage != ""){
            viewModel.calculate(currentMileage, refuelValue)
            }else{
                Toast.makeText(context, "Введите значение", Toast.LENGTH_LONG).show()
            }
        }

        binding.mfSaveButton.setOnClickListener {
            //Клик по кнопке сохранить
            viewModel.save(currentMileage, refuelValue)
        }
    }
}
