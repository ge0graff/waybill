package com.example.waybill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waybill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding_main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding_main.root)

        binding_main.bottonNavigation.selectedItemId = R.id.main
        binding_main.bottonNavigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.my_cars -> {
                    val car = Intent(this, MyCarActivity::class.java)
                    startActivity(car)
                                    }
                R.id.my_list -> {
                    val list = Intent(this, MyListActivity::class.java)
                    startActivity(list)
                }
            }
            true
        }
    }
}


