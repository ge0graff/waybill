package com.example.waybill.presentation.ui.fragments.waybillfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waybill.R
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.FragmentWaybillBinding
import com.example.waybill.presentation.ui.recyclerviews.lists.WaybillAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaybillFragment : Fragment() {

    val viewModel: WaybillFragmentViewModel by viewModels()
    private var _binding: FragmentWaybillBinding? = null
    private val binding get () = _binding!!

    private val waybillAdapter = WaybillAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWaybillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.waybillRcView.adapter = waybillAdapter
        binding.waybillRcView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.waybills.observe(viewLifecycleOwner, Observer {
            waybillAdapter.submitList(it)
        })

        init()
    }


    private fun init() {
        if(SelectedCar.id == -1) {
            showSelectCarDialog()
        }
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
            .setIcon(R.drawable.ic_baseline_list_alt_24)
            .setTitle("Путевые листы")
            .setMessage("Выберете автомобиль")
            .setPositiveButton("Выбрать", listener)
            .setNegativeButton("Отмена", listener)
            .create()
        dialog.show()
    }
}


