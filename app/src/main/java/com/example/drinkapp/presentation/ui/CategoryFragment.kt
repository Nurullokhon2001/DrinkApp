package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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

    private lateinit var ctgrArary: CategoriesModel
    private lateinit var adapter: NumberAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var vm: CategoriesVm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        initViews(view)

        vm.getCtgr()
        vm.ctgr.observe(viewLifecycleOwner) {
            ctgrArary = it.body()!!
            adapter = NumberAdapter(requireActivity(), ctgrArary)
            viewPager2.adapter = adapter
            viewPager2.offscreenPageLimit = 7
            Log.e("onCreate", ctgrArary.categoriesNameModels.size.toString())
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = ctgrArary.categoriesNameModels[position].strCategory
            }.attach()
        }

        tabLayout.selectTab(tabLayout.getTabAt(5))
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val mSearch = menu.findItem(R.id.action_search)
        val mSearchView: SearchView = mSearch.actionView as SearchView

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.e("onQueryTextSubmit", "onQueryTextSubmit: doooooo")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.e("onQueryTextSubmit", "onQueryTextSubmit: $newText")
                if (newText.isNotEmpty()) {
//                    getDrinksByName
//                    vm.getDrinksByName(newText).observe(viewLifecycleOwner) {
//                        Log.e("onCreateView", "onCreateView: ${it.body()!!.drinks.size}")
////                        adapter.setData(it.body()!!.drinks.map {
////                            Drink(
////                                it.idDrink,
////                                it.strDrink,
////                                it.strDrinkThumb
////                            )
////                        } as ArrayList<Drink>)
//                    }
                    tabLayout.visibility = View.GONE
                } else {
                    tabLayout.visibility = View.VISIBLE
                }
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initViews(view: View) {
        viewPager2 = view.findViewById(R.id.viewpager)
        tabLayout = view.findViewById(R.id.tab_layout)
        vm = ViewModelProvider(this).get(CategoriesVm::class.java)
    }

}