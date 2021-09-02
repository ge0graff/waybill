package com.example.waybill.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waybill.R
import com.example.waybill.cars.Car
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.databinding.FragmentCarsBinding
import com.example.waybill.dialogs.AddCarDialogFragment
import kotlinx.android.synthetic.main.fragment_cars.*


class CarsFragment : Fragment(R.layout.fragment_cars) {
    private var car_binding: FragmentCarsBinding? = null
    private val binding get() = car_binding!!
    val adapter = CarsRecyclerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        car_binding = FragmentCarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rc_view_my_car.layoutManager = LinearLayoutManager(context)
        rc_view_my_car.adapter = adapter
        addCars()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rc_view_my_car.layoutManager = LinearLayoutManager(this.context)
        rc_view_my_car.adapter = adapter

    }


    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance() = CarsFragment()

    }

    fun addCars(){
        binding.addCar.setOnClickListener {
            val dialog = AddCarDialogFragment(adapter)
            dialog.show(parentFragmentManager, "customDialog")

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        car_binding = null
    }


}