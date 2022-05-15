package com.example.drinkapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drinkapp.R
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

}

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.toolbar_menu, menu)
//        val mSearch = menu.findItem(R.id.action_search)
//        val mSearchView: SearchView = mSearch.actionView as SearchView
//
//        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                Log.e("onQueryTextSubmit", "onQueryTextSubmit: doooooo")
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                Log.e("onQueryTextSubmit", "onQueryTextSubmit: $newText")
//                if (newText.isNotEmpty()) {
//                    vm.getDrinksByName(newText).observe(viewLifecycleOwner) {
//                        Log.e("onCreateView", "onCreateView: ${it.body()!!.drinks.size}")
//                        adapter.setData(it.body()!!.drinks.map {
//                            Drink(
//                                it.idDrink,
//                                it.strDrink,
//                                it.strDrinkThumb
//                            )
//                        } as ArrayList<Drink>)
//                    }
////                    tabLayout.visibility = View.GONE
//                } else {
////                    tabLayout.visibility = View.VISIBLE
//                }
//                return false
//            }
//        })
//        super.onCreateOptionsMenu(menu, inflater)
//    }