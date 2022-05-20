package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkapp.App
import com.example.drinkapp.presentation.adapter.CategoryAdapter
import com.example.drinkapp.R
import com.example.drinkapp.data.model.CategoriesDBModel
import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.presentation.vm.MainViewModel
import com.example.drinkapp.presentation.vm.RoomViewModel
import com.example.drinkapp.presentation.vm.RoomViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private lateinit var vm: MainViewModel
    private lateinit var ctgrArray: CategoriesModel
    private lateinit var adapter: CategoryAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val roomViewModel by viewModels<RoomViewModel> {
        RoomViewModelFactory((activity?.application as App).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initViews(view)

        vm.getCtgr()
        vm.ctgr.observe(viewLifecycleOwner) {
            it?.let {
                ctgrArray = it.body()!!
                adapter = CategoryAdapter(requireActivity(), ctgrArray.categoriesNameModels)
                viewPager.adapter = adapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = ctgrArray.categoriesNameModels[position].strCategory
                }.attach()

                roomViewModel.insertCategories(ctgrArray.categoriesNameModels.map {
                    CategoriesDBModel(
                        strCategory = it.strCategory
                    )
                } )

            }
        }
        return view
    }

    private fun initViews(view: View) {
        viewPager = view.findViewById(R.id.viewpager)
        tabLayout = view.findViewById(R.id.tab_layout)
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}