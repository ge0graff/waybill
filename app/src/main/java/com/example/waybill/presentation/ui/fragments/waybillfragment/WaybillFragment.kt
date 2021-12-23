package com.example.waybill.presentation.ui.fragments.waybillfragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waybill.data.managers.DatabaseManagerHolder
import com.example.waybill.data.objects.DataObject
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.databinding.FragmentWaybillBinding
import com.example.waybill.presentation.ui.recyclerviews.lists.WaybillRecyclerAdapter
import java.util.*


//class WaybillFragment : Fragment() {
//
//    companion object{
//        fun getNewInstance(arg: Bundle?): WaybillFragment {
//            val waybillFragment = WaybillFragment()
//            waybillFragment.arguments = arg
//            return waybillFragment
//        }
//    }
//    private lateinit var binding: FragmentWaybillBinding
//    private val databaseManager = DatabaseManagerHolder.databaseManager
//    private val carId = SelectedCar.id
//    private var waybillList = databaseManager.getCarWaybill(carId, DataObject.mouthId) ?: listOf()
//    lateinit var adapter: WaybillRecyclerAdapter
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        adapter = WaybillRecyclerAdapter(waybillList)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentWaybillBinding.inflate(inflater)
//        return binding.root
//    }
//
//    override fun onStart() {
//        super.onStart()
//        binding.waybillRcView.adapter = adapter
//        binding.waybillRcView.layoutManager = LinearLayoutManager(requireContext())
//        val data = Calendar.getInstance().time
//    }
//}