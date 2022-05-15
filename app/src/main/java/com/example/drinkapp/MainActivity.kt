package com.example.drinkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.example.drinkapp.presentation.ui.CategoryFragment
import com.example.drinkapp.presentation.ui.FavoriteFragment
import com.example.drinkapp.presentation.ui.HistoryFragment
import com.example.drinkapp.presentation.ui.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btn_view)
//        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.category,R.id.history,R.id.favorite,R.id.settings))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        bottomNavigationView.setupWithNavController(navController)

        val   bottomNavigationView : BottomNavigationView = findViewById(R.id.btn_view)
        setCurrentFragment(CategoryFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.category->setCurrentFragment(CategoryFragment())
                R.id.history->setCurrentFragment(HistoryFragment())
                R.id.settings->setCurrentFragment(SettingsFragment())
                R.id.favorite->setCurrentFragment(FavoriteFragment())
            }
            true
        }

    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment,fragment)
            commit()
        }


}