package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.DrinkDetails
import com.example.drinkapp.presentation.adapter.DrinkAdapter
import com.example.drinkapp.presentation.vm.CategoriesVm
import com.example.drinkapp.presentation.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CategoryFragment : Fragment() {

    lateinit var ctgrArary: CategoriesModel
    lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        val viewPager2: ViewPager2 = view.findViewById(R.id.viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        val progress = view.findViewById<ProgressBar>(R.id.progress)

        val click = object : DrinkAdapter.DrinkOnclick {
            override fun clickItem(id: Int) {
                //  findNavController().navigate(R.id.action_category_to_detailFragment)
                val action = CategoryFragmentDirections.actionCategoryToDetailFragment(id)
                Navigation.findNavController(view).navigate(action)
//                vm.getDetailsDrinkById(id.toString()).observe(viewLifecycleOwner) {
//
//                }
            }
        }
        vm.getCtgr()
        vm.ctgr.observe(viewLifecycleOwner) {
            ctgrArary = it.body()!!
            adapter = ViewPagerAdapter(ctgrArary.categoriesNameModels, requireContext(), click)
            viewPager2.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = ctgrArary.categoriesNameModels[position].strCategory
            }.attach()
        }

        vm.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                viewPager2.visibility = View.GONE
                progress.visibility = View.VISIBLE
            } else {
                viewPager2.visibility = View.VISIBLE
                progress.visibility = View.GONE
            }
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    vm.getDrinks(ctgrArary.categoriesNameModels[tab.position].strCategory)
                    vm.drinks.observe(viewLifecycleOwner) {
                        Log.e("onCreateView", "onCreateView: ${it.body()!!.drinks.size}")
                        adapter.setData(it.body()!!.drinks as ArrayList<Drink>)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewPager2.currentItem = 4
            }
        })

        tabLayout.selectTab(tabLayout.getTabAt(5))

        return view
    }


}