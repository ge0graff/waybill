package com.example.waybill.presentation.ui.fragments.mainfragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.waybill.data.Database
import com.example.waybill.data.managers.DatabaseManager
import com.example.waybill.data.managers.DatabaseManagerHolder

class MainFragmentViewModelFactory(): ViewModelProvider.Factory{

    private val database by lazy(LazyThreadSafetyMode.NONE) {
        DatabaseManagerHolder
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(database) as T
    }


}