package com.example.waybill.domain


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.waybill.R
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.presentation.ui.recyclerviews.cars.CarForwardClick
import com.example.waybill.data.model.Car
import com.example.waybill.data.objects.DataObject
import com.example.waybill.databinding.ActivityMainBinding
import com.example.waybill.presentation.ui.fragments.*
import com.example.waybill.presentation.ui.recyclerviews.lists.ListForwardClick
import com.example.waybill.presentation.ui.recyclerviews.lists.Mouths
import java.text.DateFormat
import java.util.*


class MainActivity() : AppCompatActivity(), CarForwardClick, ListForwardClick {
    private lateinit var bindingMain: ActivityMainBinding
    private val mainFragment = MainFragment.newInstance()
    private val carsFragment = CarsFragment.newInstance()
    private val listFragment = ListFragment.newInstance()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_holder,
            MainFragment.newInstance()).commit()
        setupNavigation()
    }

    private fun setupNavigation() {
        bindingMain.bottonNavigation.selectedItemId = R.id.main
        bindingMain.bottonNavigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.main -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_holder, mainFragment)
                        .addToBackStack(null).commit()
                }
                R.id.my_cars -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_holder, carsFragment)
                        .addToBackStack(null).commit()
                }
                R.id.my_list -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_holder, listFragment)
                        .addToBackStack(null).commit()
                }
            }
            true
        }
    }

    override fun carForwardClick(car: Car) {
        val bundle = Bundle()
        bundle.putString("name", car.name)
        bundle.putString("mileage", car.mileage)
        bundle.putString("cSum", car.consumption_summer)
        bundle.putString("cWin", car.consumption_winter)
        bundle.putString("fuel", car.fuel_value)
        CarInfoFragment.getNewInstance(arg = bundle)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_holder,
            CarInfoFragment.getNewInstance(arg = bundle)).addToBackStack(null).commit()
    }

    override fun listForwardClick(mouths: Mouths) {
            DataObject.mouthId = mouths.id
            val bundle = Bundle()
        bundle.putString("mouthId", mouths.id)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
        WaybillFragment.getNewInstance(arg = bundle)).addToBackStack(null).commit()
    }


}


