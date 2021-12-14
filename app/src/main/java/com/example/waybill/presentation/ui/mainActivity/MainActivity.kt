package com.example.waybill.presentation.ui.mainActivity


import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.waybill.R
import com.example.waybill.data.objects.DataObject
import com.example.waybill.databinding.ActivityMainBinding
import com.example.waybill.presentation.ui.fragments.waybillfragment.WaybillFragment
import com.example.waybill.presentation.ui.recyclerviews.lists.ListForwardClick
import com.example.waybill.presentation.ui.recyclerviews.lists.Mouths
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : AppCompatActivity(), ListForwardClick{


    private lateinit var bindingMain: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        val buttonNavigationView = findViewById<BottomNavigationView>(R.id.buttonNavigation)
        val navController = findNavController(R.id.fragment_holder)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.carsFragment, R.id.listFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        val a = resources

        buttonNavigationView.setupWithNavController(navController)
    }

    override fun listForwardClick(mouths: Mouths) {
        DataObject.mouthId = mouths.id
        val bundle = Bundle()
        bundle.putString("mouthId", mouths.id)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
            WaybillFragment.getNewInstance(arg = bundle)).addToBackStack(null).commit()
    }

}

const val ADD_CAR_RESULT_OK = Activity.RESULT_FIRST_USER















//        supportFragmentManager.beginTransaction().replace(
//            R.id.fragment_holder,
//            MainFragment.newInstance()).commit()
//        setupNavigation()


//    private fun setupNavigation() {
//        bindingMain.bottonNavigation.selectedItemId = R.id.main
//        bindingMain.bottonNavigation.setOnNavigationItemSelectedListener{
//            when(it.itemId){
//                R.id.main -> {
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.fragment_holder, mainFragment)
//                        .addToBackStack(null).commit()
//                }
//                R.id.my_cars -> {
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.fragment_holder, carsFragment)
//                        .addToBackStack(null).commit()
//                }
//                R.id.my_list -> {
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.fragment_holder, listFragment)
//                        .addToBackStack(null).commit()
//                }
//            }
//            true
//        }
//    }
//    override fun carForwardClick(car: Car) {
//        val bundle = Bundle()
//        bundle.putString("name", car.name)
//        bundle.putString("mileage", car.mileage)
//        bundle.putString("cSum", car.consumption_summer)
//        bundle.putString("cWin", car.consumption_winter)
//        bundle.putString("fuel", car.fuel_value)
//        CarInfoFragment.getNewInstance(arg = bundle)
//        supportFragmentManager.beginTransaction().replace(
//            R.id.fragment_holder,
//            CarInfoFragment.getNewInstance(arg = bundle)).addToBackStack(null).commit()
//    }
//


