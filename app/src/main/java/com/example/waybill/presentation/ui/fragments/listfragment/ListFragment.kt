package com.example.waybill.presentation.ui.fragments.listfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waybill.databinding.FragmentListBinding
import com.example.waybill.presentation.ui.recyclerviews.lists.ListRecyclerAdapter
import com.example.waybill.presentation.ui.recyclerviews.lists.Mouths


class ListFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListFragmentLViewModel
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
        viewModel = ViewModelProvider(this)
            .get(ListFragmentLViewModel::class.java)
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        binding.lstRcv.layoutManager = LinearLayoutManager(requireContext())
        binding.lstRcv.hasFixedSize()
        binding.lstRcv.adapter = adapter
        viewModel.fillList(mouths)
    }

    override fun onStop() {
        super.onStop()
        mouths.clear()
    }

}
