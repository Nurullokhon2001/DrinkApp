package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.App
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.DrinkAdapter
import com.example.drinkapp.presentation.vm.CategoriesVm
import com.example.drinkapp.presentation.vm.HistoryVM
import com.example.drinkapp.presentation.vm.HistoryViewModelFactory


class HistoryFragment : Fragment() {

    private lateinit var rv: RecyclerView
    private lateinit var adapter: DrinkAdapter
    private val historyVm: HistoryVM by viewModels {
        HistoryViewModelFactory((activity?.application as App).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        rv = view.findViewById(R.id.rv)
        adapter = DrinkAdapter(requireContext(), click)
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(requireContext(), 2)

        historyVm.allHistory.observe(viewLifecycleOwner) { it ->
            it?.let { it ->
                adapter.setData(it.map { Drink(it.idDrink,it.drinkName,it.drinkImg) } as ArrayList<Drink>)
            }
        }

        return view
    }


    private val click = object : DrinkAdapter.DrinkOnclick {
        override fun clickItem(model: Drink) {
            val ldf = DetailFragment()
            val args = Bundle()
            args.putString("id", model.idDrink)
            ldf.arguments = args
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.nav_host_fragment, ldf)
                addToBackStack(null)
                commit()
            }
        }
    }

}