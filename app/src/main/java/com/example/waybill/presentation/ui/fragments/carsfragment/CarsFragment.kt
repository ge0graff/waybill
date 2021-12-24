package com.example.waybill.presentation.ui.fragments.carsfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
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

    companion object {
        @JvmStatic
        fun newInstance() = CarsFragment()
    }

    val viewModel: CarsFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCarsBinding.bind(view)
        val carsAdapter = CarsAdapter(this)

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

            viewModel.cars.observe(viewLifecycleOwner){
                carsAdapter.submitList(it)
            }

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.carsEvent.collect { event ->
                    when(event){
                        is CarsFragmentViewModel.CarsEvent.ShowUndoDeleteTaskMessage -> {

                            val listener = DialogInterface.OnClickListener { dialog, which ->
                                when(which){
                                    DialogInterface.BUTTON_POSITIVE -> viewModel.onUndoDeleteClick(event.car)
                                    DialogInterface.BUTTON_NEGATIVE -> dialog.dismiss()
                                }
                            }
                            val dialog = AlertDialog.Builder(requireContext())
                                .setCancelable(false)
                                .setIcon(R.drawable.ic_baseline_auto_delete)
                                .setTitle("Автомобиль удален")
                                .setMessage("Отменить удаление?")
                                .setNegativeButton("Нет", listener)
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

            addCarButton.setOnClickListener {
                viewModel.onAddCarClick()
            }
        }
    }

    override fun onCarDetails(car: Car) {
        val bundle = Bundle()
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
    
}

