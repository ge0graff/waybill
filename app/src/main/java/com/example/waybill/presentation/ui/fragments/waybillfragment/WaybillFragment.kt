package com.example.waybill.presentation.ui.fragments.waybillfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
    }
}


