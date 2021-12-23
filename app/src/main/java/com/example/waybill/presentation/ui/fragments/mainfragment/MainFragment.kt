package com.example.waybill.presentation.ui.fragments.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : Fragment(){

    val viewModel: MainFragmentViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get () = _binding!!

    private var currentMileage = ""
    private var refuelValue = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mfCarName.text = SelectedCar.name

        viewModel.dalyMileageValueLive.observe(viewLifecycleOwner, Observer {
            binding.mfCarDailyMileageValue.text = it
        })

        viewModel.fuelValueLive.observe(viewLifecycleOwner, Observer {
            binding.mfFuelValue.text = it
        })

        binding.mfCalculate.setOnClickListener {
            currentMileage = binding.mfMileageValue.text.toString()
            refuelValue = binding.mfRefuelValue.text.toString()
            if(currentMileage != ""){
            viewModel.calculate(currentMileage, refuelValue)
            }else{
                Toast.makeText(context, "Введите значение", Toast.LENGTH_LONG).show()
            }
        }

        binding.mfSaveButton.setOnClickListener {
            viewModel.save(currentMileage, refuelValue)
        }
    }
}
