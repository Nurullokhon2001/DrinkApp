package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.presentation.vm.CategoriesVm
import com.example.myapplication.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CategoryFragment : Fragment() {

    lateinit var ctgrArary: CategoriesModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        val viewPager2: ViewPager2 = view.findViewById(R.id.viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val vm = ViewModelProvider(this).get(CategoriesVm::class.java)


        vm.getCtgr().observe(viewLifecycleOwner) {
            ctgrArary = it.body()!!
            viewPager2.adapter = ViewPagerAdapter(ctgrArary.categoriesNameModels)

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = ctgrArary.categoriesNameModels[position].strCategory


            }.attach()
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    vm.getDrinks(ctgrArary.categoriesNameModels[tab.position].strCategory)
                        .observe(viewLifecycleOwner) {
                            Log.e("onCreateView", "onCreateView: ${it.body()!!.drinks.size}")
                        }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        return view
    }


}