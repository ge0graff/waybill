package com.example.waybill.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waybill.R
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.Cars
import com.example.waybill.databinding.FragmentCarInfoBinding


class CarInfoFragment : Fragment(R.layout.fragment_cars) {
    private var carInfo_binding: FragmentCarInfoBinding? = null
    private val binding get() = carInfo_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        carInfo_binding = FragmentCarInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.carInfoCarNameText.text = arguments?.getString("title")

        val bundle = this.arguments
        if (bundle != null) {

            binding.carInfoCarNameText.text = bundle.getString("данные")
        } else {
            binding.carInfoCarNameText.text = "Неудача"
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CarInfoFragment()
            }

}
