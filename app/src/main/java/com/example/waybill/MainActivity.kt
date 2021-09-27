package com.example.waybill


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.Cars
import com.example.waybill.data.CarsDatabase
import com.example.waybill.databinding.ActivityMainBinding
import com.example.waybill.fragments.*
import java.text.FieldPosition


class MainActivity : AppCompatActivity(), CarsRecyclerAdapter.ClickEventHandler {
    lateinit var binding_main: ActivityMainBinding

    val mainFragment = MainFragment.newInstance()
    val carsFragment = CarsFragment.newInstance()
    val listFragment = ListFragment.newInstance()
    val carInfoFragment = CarInfoFragment.newInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding_main.root)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
            MainFragment.newInstance()).commit()
        navigation()
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

    override fun forwardClick(holder: CarsRecyclerAdapter.CarsHolder) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
            carInfoFragment).addToBackStack(null).commit()
    }


}


