package com.example.waybill.presentation.ui.fragments.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waybill.R
import com.example.waybill.data.model.Mouths
import com.example.waybill.data.objects.DataObject
import com.example.waybill.databinding.FragmentListBinding
import com.example.waybill.presentation.ui.recyclerviews.lists.ListForwardClick
import com.example.waybill.presentation.ui.recyclerviews.lists.ListRecyclerAdapter


class ListFragment : Fragment(), ListForwardClick {

    val viewModel: ListFragmentLViewModel by viewModels()
    private var mouths = ArrayList<Mouths>()

    private var _binding: FragmentListBinding? = null
    private val binding get () = _binding!!

    private var adapter = ListRecyclerAdapter(this, mouths)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
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

    override fun listForwardClick(mouths: Mouths) {
        DataObject.mouthId = mouths.id
        val bundle = Bundle()
        bundle.putString("mouthId", mouths.id)
        findNavController().navigate(R.id.action_listFragment_to_waybillFragment, bundle)
    }

}
