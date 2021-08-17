package com.example.waybill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waybill.cars.CarsAdapter
import com.example.waybill.databinding.ActivityMyCarBinding
import com.example.waybill.dialogs.AddDialogHelper


class MyCarActivity : AppCompatActivity() {
    lateinit var bindingCar: ActivityMyCarBinding
    val adapter = CarsAdapter()
    val addDialogHelper = AddDialogHelper(this, adapter)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingCar = ActivityMyCarBinding.inflate(layoutInflater)
        setContentView(bindingCar.root)
        navigation()
        init()

        bindingCar.addCar.setOnClickListener{
            addDialogHelper.createAddDialog()
        }

    }

    fun navigation (){
        bindingCar.bottonNavigationCar.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.my_list -> {
                    val list = Intent(this, MyListActivity::class.java)
                    startActivity(list)
                }
                R.id.main -> {
                    val main = Intent(this, MainActivity::class.java)
                    startActivity(main)
                }
            }
            true
        }
    }

    fun init(){
        bindingCar.rcViewMyCar.layoutManager = LinearLayoutManager(this)
        bindingCar.rcViewMyCar.adapter = adapter
    }

}
