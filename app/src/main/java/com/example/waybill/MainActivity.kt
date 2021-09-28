package com.example.waybill


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.room.Room
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.Cars
import com.example.waybill.data.CarsDatabase
import com.example.waybill.databinding.ActivityMainBinding
import com.example.waybill.fragments.*


class MainActivity : AppCompatActivity(), CarsRecyclerAdapter.ClickEventHandler {
    lateinit var binding_main: ActivityMainBinding


    val mainFragment = MainFragment.newInstance()
    val carsFragment = CarsFragment.newInstance()
    val listFragment = ListFragment.newInstance()
//    val carInfoFragment = CarInfoFragment.getNewInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding_main.root)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
            MainFragment.newInstance()).commit()
        navigation()
        val carInfoFg = CarInfoFragment()
        carInfoFg.arguments?.putString("test", "carName")
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
        binding_main.bottonNavigation.selectedItemId = R.id.main
        binding_main.bottonNavigation.setOnNavigationItemSelectedListener{
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


}


