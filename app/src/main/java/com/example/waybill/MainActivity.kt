package com.example.waybill


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.Cars
import com.example.waybill.data.CarsDatabase
import com.example.waybill.data.carselect.SelectedCar
import com.example.waybill.databinding.ActivityMainBinding
import com.example.waybill.fragments.*


class MainActivity() : AppCompatActivity(), CarsRecyclerAdapter.ClickEventHandler {
    private lateinit var bindingMain: ActivityMainBinding
    val mainFragment = MainFragment.newInstance()
    private val carsFragment = CarsFragment.newInstance()
    private val listFragment = ListFragment.newInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
            MainFragment.newInstance()).commit()
        navigation()
        carSelect()
    }

    companion object {
        @Volatile
        private var INSTANCE: CarsDatabase? = null
        fun getInstance(context: Context): CarsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CarsDatabase::class.java,
                        "car_table"
                    )
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private fun navigation (){
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

    override fun forwardClick(car: Cars) {
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

    private fun carSelect(){
        val pref = getSharedPreferences("Car", 0)

        SelectedCar.id = pref.getInt("prefId", -1)
        SelectedCar.name = pref.getString("prefNM", "")!!
        SelectedCar.mileage = pref.getString("prefML", "")!!
        SelectedCar.consumption_summer = pref.getString("prefCS", "")!!
        SelectedCar.consumption_winter = pref.getString("prefCW", "")!!
        SelectedCar.fuel_value = pref.getString("prefFV", "")!!


    }



}


