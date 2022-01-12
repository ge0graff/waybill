package com.example.waybill.presentation.ui.fragments.carsfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waybill.R
import com.example.waybill.data.model.Car
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.FragmentCarsBinding
import com.example.waybill.presentation.ui.dialogs.AddCarDialogFragment
import com.example.waybill.presentation.ui.recyclerviews.cars.CarForwardClick
import com.example.waybill.presentation.ui.recyclerviews.cars.CarsAdapter
import com.example.waybill.presentation.utils.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CarsFragment : Fragment(R.layout.fragment_cars), CarForwardClick{

    val viewModel: CarsFragmentViewModel by viewModels()

    private var _binding: FragmentCarsBinding? = null
    private val binding get () = _binding!!

    val carsAdapter = CarsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        touchHelper()
        viewModelEvent()
            viewModel.cars.observe(viewLifecycleOwner){
                carsAdapter.submitList(it)
            }
            binding.addCarButton.setOnClickListener {
                viewModel.onAddCarClick()
            }
        }

    private fun touchHelper() {
        binding.apply {
            carsRv.apply {
                adapter = carsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)

                ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val car = carsAdapter.currentList[viewHolder.adapterPosition]
                        viewModel.onRemoveCar(car)
                    }

                }
                ).attachToRecyclerView(carsRv)
            }
        }
    }

    private fun viewModelEvent() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.carsEvent.collect { event ->
                when(event){
                    is CarsFragmentViewModel.CarsEvent.ShowUndoDeleteTaskMessage -> {
                        val listener = DialogInterface.OnClickListener { dialog, which ->
                            when(which){
                                DialogInterface.BUTTON_NEGATIVE -> viewModel.onUndoDeleteClick(event.car)
                                DialogInterface.BUTTON_POSITIVE -> dialog.dismiss()
                            }
                        }
                        val dialog = AlertDialog.Builder(requireContext())
                            .setCancelable(false)
                            .setIcon(R.drawable.ic_baseline_auto_delete)
                            .setTitle("Автомобиль удален")
                            .setMessage("Вы уверены?")
                            .setNegativeButton("Отменить", listener)
                            .setPositiveButton("Да", listener)
                            .create()
                        dialog.show()
                    }
                    is CarsFragmentViewModel.CarsEvent.NavigateToAddCarScreen -> {
                        val dialog = AddCarDialogFragment()
                        dialog.show(parentFragmentManager, "customDialog")
                    }
                }.exhaustive
            }
        }
    }

    override fun onCarDetails(car: Car) {
        val bundle = Bundle()
        bundle.putInt("token", 0)
        bundle.putString("name", car.name)
        bundle.putString("mileage", car.mileage)
        bundle.putString("cSum", car.consumption_summer)
        bundle.putString("cWin", car.consumption_winter)
        bundle.putString("fuel", car.fuel_value)
        findNavController().navigate(R.id.action_carsFragment_to_carInfoFragment, bundle)
    }

    override fun onCarSelect(car: Car) {
        SelectedCar.apply {
            id = car.id ?: 0
            name = car.name
            mileage = car.mileage
            consumption_summer = car.consumption_summer
            consumption_winter = car.consumption_winter
            fuel_value = car.fuel_value
        }
    }

    override fun onLongClick(car: Car) {
        val bundle = Bundle()
        bundle.putInt("car_id", car.id!!)
        bundle.putInt("token", 1)
        bundle.putString("button_text", "Сохранить")
        bundle.putString("edit_text", "Редактирование")
        bundle.putString("name", car.name)
        bundle.putString("mileage", car.mileage)
        bundle.putString("cSum", car.consumption_summer)
        bundle.putString("cWin", car.consumption_winter)
        bundle.putString("fuel", car.fuel_value)

        val dialog = AddCarDialogFragment()

        dialog.arguments = bundle
        dialog.show(parentFragmentManager, "customDialog")
    }

}

