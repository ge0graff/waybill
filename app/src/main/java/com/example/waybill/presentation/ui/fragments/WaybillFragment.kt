package com.example.waybill.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waybill.databinding.FragmentWaybillBinding


class WaybillFragment : Fragment() {

    companion object{
        fun getNewInstance(arg: Bundle?): WaybillFragment {
            val waybillFragment = WaybillFragment()
            waybillFragment.arguments = arg
            return waybillFragment
        }
    }

    private lateinit var binding: FragmentWaybillBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWaybillBinding.inflate(inflater)
        return binding.root
    }

}