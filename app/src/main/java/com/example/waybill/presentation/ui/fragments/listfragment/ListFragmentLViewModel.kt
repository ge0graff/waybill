package com.example.waybill.presentation.ui.fragments.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waybill.data.model.Mouths
import kotlinx.coroutines.launch

class ListFragmentLViewModel(): ViewModel() {

    fun fillList(mouths: ArrayList<Mouths>) = viewModelScope.launch{
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
    }
}