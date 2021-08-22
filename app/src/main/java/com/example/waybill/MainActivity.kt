package com.example.waybill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waybill.databinding.ActivityMainBinding
import com.example.waybill.fragments.CarsFragment
import com.example.waybill.fragments.ListFragment
import com.example.waybill.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding_main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding_main.root)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
            MainFragment.newInstance()).commit()
        navigation()

    }

    private fun navigation (){
        binding_main.bottonNavigation.selectedItemId = R.id.main
        binding_main.bottonNavigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.main -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
                        MainFragment.newInstance()).commit()
                }
                R.id.my_cars -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
                       CarsFragment.newInstance()).commit()
                }
                R.id.my_list -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
                        ListFragment.newInstance()).commit()
                }
            }
            true
        }

    }
}


