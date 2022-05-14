package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
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
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var vm: CategoriesVm
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        initViews(view)

        val click = object : DrinkAdapter.DrinkOnclick {
            override fun clickItem(id: Int) {
                val action = CategoryFragmentDirections.actionCategoryToDetailFragment(id)
                Navigation.findNavController(view).navigate(action)

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
                    vm.getDrinksByName(newText).observe(viewLifecycleOwner) {
                        Log.e("onCreateView", "onCreateView: ${it.body()!!.drinks.size}")
                        adapter.setData(it.body()!!.drinks.map { Drink(it.idDrink,it.strDrink,it.strDrinkThumb) } as ArrayList<Drink>)
                    }
                    tabLayout.visibility = View.GONE
                } else{
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
        progress = view.findViewById(R.id.progress)
    }

}