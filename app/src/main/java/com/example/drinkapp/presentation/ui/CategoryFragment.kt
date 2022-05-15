package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkapp.NumberAdapter
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.DrinkAdapter
import com.example.drinkapp.presentation.adapter.ViewPagerAdapter
import com.example.drinkapp.presentation.vm.CategoriesVm
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CategoryFragment : Fragment() {


    private lateinit var vm: CategoriesVm
    private lateinit var ctgrArary: CategoriesModel
    private lateinit var adapter: NumberAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
//        initViews(view)

        viewPager = view.findViewById(R.id.viewpager)

        tabLayout = view.findViewById(R.id.tab_layout)
        vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        vm.getCtgr()
        vm.ctgr.observe(viewLifecycleOwner) {
            ctgrArary = it.body()!!
            adapter = NumberAdapter(requireActivity(),ctgrArary)
            viewPager.adapter = adapter
            Log.e("onCreate", ctgrArary.categoriesNameModels.size.toString())
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = ctgrArary.categoriesNameModels[position].strCategory
            }.attach()
        }


        tabLayout.selectTab(tabLayout.getTabAt(5))

        return view
    }


//    private fun initViews(view: View) {
//        viewPager = view.findViewById(R.id.viewpager)
//        tabLayout = view.findViewById(R.id.tab_layout)
//        vm = ViewModelProvider(this).get(CategoriesVm::class.java)
//    }

}