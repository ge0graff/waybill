package com.example.waybill.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waybill.databinding.FragmentListBinding
import com.example.waybill.presentation.ui.recyclerviews.lists.ListForwardClick
import com.example.waybill.presentation.ui.recyclerviews.lists.ListRecyclerAdapter
import com.example.waybill.presentation.ui.recyclerviews.lists.Mouths


class ListFragment : Fragment() {

    private var mouths = ArrayList<Mouths>()
    private lateinit var binding: FragmentListBinding
    lateinit var adapter: ListRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = ListRecyclerAdapter(context, mouths)
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
            add(Mouths("Январь", "01"))
            add(Mouths("Февраль", "02"))
            add(Mouths("Март", "03"))
            add(Mouths("Апрель", "04"))
            add(Mouths("Май", "05"))
            add(Mouths("Июнь", "06"))
            add(Mouths("Июль", "07"))
            add(Mouths("Август", "08"))
            add(Mouths("Сентябрь", "09"))
            add(Mouths("Октябрь", "10"))
            add(Mouths("Ноябрь", "11"))
            add(Mouths("Декабрь", "12"))
        }

        binding.lstRcv.layoutManager = LinearLayoutManager(requireContext())
        binding.lstRcv.hasFixedSize()
        binding.lstRcv.adapter = adapter
    }

}