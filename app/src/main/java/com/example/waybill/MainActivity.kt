package com.example.waybill


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.model.Car
import com.example.waybill.data.carselect.SelectedCar
import com.example.waybill.data.manager.DatabaseManagerHolder
import com.example.waybill.databinding.ActivityMainBinding
import com.example.waybill.fragments.*


class MainActivity() : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding
    private val mainFragment = MainFragment.newInstance()
    private val carsFragment = CarsFragment.newInstance()
    private val listFragment = ListFragment.newInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
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

    fun forwardClick(car: Car) {
        val bundle = Bundle()
        bundle.putString("name", car.name)
        bundle.putString("mileage", car.mileage)
        bundle.putString("cSum", car.consumption_summer)
        bundle.putString("cWin", car.consumption_winter)
        bundle.putString("fuel", car.fuel_value)
        CarInfoFragment.getNewInstance(arg = bundle)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
            CarInfoFragment.getNewInstance(arg = bundle)).addToBackStack(null).commit()
    }
}


