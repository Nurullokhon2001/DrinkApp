package com.example.drinkapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkapp.R
import com.example.drinkapp.presentation.vm.CategoriesVm
import com.example.myapplication.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CategoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        val viewPager2: ViewPager2 = view.findViewById(R.id.viewpager)


        val vm = ViewModelProvider(this).get(CategoriesVm::class.java)

        vm.getCtgr().observe(viewLifecycleOwner) {
//            Log.e("onCreateView", "onCreateView: ${.size} ", )
            val array = it.body()!!.drinks
            viewPager2.adapter = ViewPagerAdapter(array)
            val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = array[position].strCategory
            }.attach()
        }

        return view
    }


}