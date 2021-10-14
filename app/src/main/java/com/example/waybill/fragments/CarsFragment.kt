package com.example.waybill.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.R
import com.example.waybill.cars.CarActionListener
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.manager.DatabaseManagerHolder
import com.example.waybill.data.model.Car
import com.example.waybill.databinding.FragmentCarsBinding
import com.example.waybill.dialogs.AddCarDialogFragment
import kotlinx.android.synthetic.main.fragment_cars.*


class CarsFragment : Fragment(R.layout.fragment_cars), CarActionListener {
    private lateinit var binding: FragmentCarsBinding
    private val databaseManager = DatabaseManagerHolder.databaseManager

    private var carList: List<Car> = databaseManager.getCars() ?: listOf()
    lateinit var adapter: CarsRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = CarsRecyclerAdapter(carList).also {
            it.carActionListener = this
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.carsRv.adapter = adapter
        val swHelper = getSwiped()
        swHelper.attachToRecyclerView(binding.carsRv)
        setupView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CarsFragment()
    }

    private fun setupView() {
        binding.addCar.setOnClickListener {
            val dialog = AddCarDialogFragment(adapter)
            dialog.show(parentFragmentManager, "customDialog")
        }
    }

    private fun getSwiped(): ItemTouchHelper{
        return ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.absoluteAdapterPosition)
            }
        }
        )

    }

    override fun removeCar(car: Car) {
        databaseManager.deleteCar(car)
    }
}