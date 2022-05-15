package com.example.drinkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.ViewPagerAdapter
import com.example.drinkapp.presentation.vm.CategoriesVm
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    private lateinit var vm: CategoriesVm
    private lateinit var ctgrArary: CategoriesModel
    private lateinit var adapter: NumberAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tab_layout2)
        vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        vm.getCtgr()
        vm.ctgr.observe(this) {
            ctgrArary = it.body()!!
            adapter = NumberAdapter(this, ctgrArary)
            viewPager.adapter = adapter
            Log.e("onCreate", ctgrArary.categoriesNameModels.size.toString())
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = ctgrArary.categoriesNameModels[position].strCategory
            }.attach()
        }


    }
}