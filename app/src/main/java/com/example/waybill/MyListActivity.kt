package com.example.waybill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waybill.databinding.ActivityMyListBinding

class MyListActivity : AppCompatActivity() {
    lateinit var binding_list: ActivityMyListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_list = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding_list.root)

        binding_list.bottonNavigationList.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.my_cars -> {
                    val car = Intent(this, MyCarActivity::class.java)
                    startActivity(car)
                }
                R.id.main -> {
                    val main = Intent(this, MainActivity::class.java)
                    startActivity(main)
                }
            }
            true
        }
    }
}