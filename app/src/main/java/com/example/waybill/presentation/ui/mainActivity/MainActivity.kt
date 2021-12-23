package com.example.waybill.presentation.ui.mainActivity


//import com.example.waybill.presentation.ui.fragments.waybillfragment.WaybillFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.waybill.R
import com.example.waybill.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : AppCompatActivity(){


    private lateinit var bindingMain: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        val buttonNavigationView = findViewById<BottomNavigationView>(R.id.buttonNavigation)
        val navController = findNavController(R.id.fragment_holder)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.carsFragment, R.id.listFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        buttonNavigationView.setupWithNavController(navController)
    }

//    override fun listForwardClick(mouths: Mouths) {
//        DataObject.mouthId = mouths.id
//        val bundle = Bundle()
//        bundle.putString("mouthId", mouths.id)
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder,
//            WaybillFragment.getNewInstance(arg = bundle)).addToBackStack(null).commit()
//    }

}
















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

//


