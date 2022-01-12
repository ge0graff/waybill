package com.example.waybill.presentation.ui.fragments.mainfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.waybill.R
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.CasomDialogBinding
import com.example.waybill.databinding.FragmentMainBinding
import com.example.waybill.presentation.utils.hideKeyboard
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(SelectedCar.id != -1) {
            binding.mfCarName.text = SelectedCar.name
        }

        binding.mfCalculate.setOnClickListener {
            calculate()
        }
    }

    private fun showCalculateResultDialog() {
        val dialogBinding = CasomDialogBinding.inflate(layoutInflater)
        val listener = DialogInterface.OnClickListener { dialog, which ->

            when (which) {
                DialogInterface.BUTTON_NEGATIVE -> dialog.dismiss()
                DialogInterface.BUTTON_POSITIVE -> addEndClear()
            }
        }

        viewModel.dalyMileageValueLive.observe(viewLifecycleOwner, Observer {
            dialogBinding.mfCarDailyMileageValue.text = it
        })

        viewModel.fuelValueLive.observe(viewLifecycleOwner, Observer {
            dialogBinding.mfFuelValue.text = it
        })

        val dialog = AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setIcon(R.drawable.ic_baseline_calculate_24)
            .setTitle(resources.getString(R.string.calculate_dialog))
            .setView(dialogBinding.root)
            .setNegativeButton("Отменить", listener)
            .setPositiveButton("Сохранить", listener)
            .create()
        dialog.show()
    }

    private fun calculate() {
        currentMileage = binding.mfMileageValue.text.toString()
        refuelValue = binding.mfRefuelValue.text.toString()
        when {
            SelectedCar.id == -1 -> {
                showSelectCarDialog()
            }
            currentMileage == "" -> {
                binding.mfMileageValue.error = resources.getString(R.string.main_fragment_mileage_error)
            }
            else -> {
                viewModel.calculate(currentMileage, refuelValue)
                hideKeyboard()
                showCalculateResultDialog()
            }
        }
    }

    private fun showConfirmationDialog() {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> dialog.dismiss()
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setIcon(R.drawable.ic_baseline_auto_delete)
            .setMessage(resources.getString(R.string.main_fragment_dialog_save_data))
            .setPositiveButton("Ок", listener)
            .create()
        dialog.show()
    }

    private fun showSelectCarDialog() {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> findNavController().navigate(R.id.carsFragment)
                DialogInterface.BUTTON_NEGATIVE -> dialog.dismiss()
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setIcon(R.drawable.ic_baseline_calculate_24)
            .setTitle(resources.getString(R.string.calculate_dialog))
            .setMessage(resources.getString(R.string.main_fragment_dialog_select_car))
            .setPositiveButton("Выбрать", listener)
            .setNegativeButton("Отмена", listener)
            .create()
        dialog.show()
    }

    private fun addEndClear() {
        viewModel.save(currentMileage, refuelValue)
        showConfirmationDialog()
        binding.mfMileageValue.setText("")
        binding.mfMileageValue.clearFocus()
        binding.mfRefuelValue.setText("")
        binding.mfRefuelValue.clearFocus()
    }
}
