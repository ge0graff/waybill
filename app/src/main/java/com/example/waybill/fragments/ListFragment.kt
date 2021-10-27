package com.example.waybill.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waybill.R
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.databinding.FragmentCarsBinding
import com.example.waybill.databinding.FragmentListBinding
import com.example.waybill.dialogs.AddCarDialogFragment
import com.example.waybill.lists.ListRecyclerAdapter
import com.example.waybill.lists.Mouths
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private var mouths = ArrayList<Mouths>()
    private lateinit var binding: FragmentListBinding
    lateinit var adapter: ListRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = ListRecyclerAdapter(mouths)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        fillList()
    }

    override fun onStop() {
        super.onStop()
        mouths.clear()
    }


    companion object {

        @JvmStatic
        fun newInstance() = ListFragment() }

    private fun fillList(){
        mouths.apply {
            add(Mouths(("Январь")))
            add(Mouths(("Февраль")))
            add(Mouths(("Март")))
            add(Mouths(("Апрель")))
            add(Mouths(("Май")))
            add(Mouths(("Июнь")))
            add(Mouths(("Июль")))
            add(Mouths(("Август")))
            add(Mouths(("Сентябрь")))
            add(Mouths(("Ноябрь")))
            add(Mouths(("Декабрь")))
        }

        binding.lstRcv.layoutManager = LinearLayoutManager(requireContext())
        binding.lstRcv.hasFixedSize()
        binding.lstRcv.adapter = adapter
    }




}