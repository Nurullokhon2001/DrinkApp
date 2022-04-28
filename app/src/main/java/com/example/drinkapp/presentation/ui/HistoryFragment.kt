package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.DrinkAdapter
import com.example.drinkapp.presentation.vm.CategoriesVm


class HistoryFragment : Fragment() {

    lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        rv = view.findViewById(R.id.rv)
        val vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        val adapter = DrinkAdapter(requireContext())
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(requireContext(),2)
        vm.getDrinks("COCKTAIL")
            .observe(viewLifecycleOwner) {
                Log.e("onCreateView", "onCreateView: ${it.body()!!.drinks.size}")
                adapter.setData(it.body()!!.drinks as ArrayList<Drink>)
            }
        return view
    }

}