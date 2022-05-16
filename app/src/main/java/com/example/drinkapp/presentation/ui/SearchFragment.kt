package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.DrinkAdapter
import com.example.drinkapp.presentation.vm.CategoriesVm
import com.google.android.material.textfield.TextInputEditText

class SearchFragment : Fragment() {

    private lateinit var progress: ProgressBar
    private lateinit var adapter: DrinkAdapter
    private lateinit var vm: CategoriesVm
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initViews(view)
        val mSearch = view.findViewById<TextInputEditText>(R.id.edit_text)

        vm.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        }

        mSearch.addTextChangedListener { it ->
            it?.let { it ->
                if (it.isNotEmpty()) {
                    vm.getDrinksByName(it.toString()).observe(viewLifecycleOwner) { drinks ->

                        if (drinks.body()?.drinks?.isEmpty() == false) {
                            Log.e("onCreateView", "onCreateView: ${drinks.body()!!.drinks.size}")
                            adapter.setData(drinks.body()!!.drinks.map {
                                Drink(
                                    it.idDrink,
                                    it.strDrink,
                                    it.strDrinkThumb
                                )
                            } as ArrayList<Drink>)
                        }

                    }
                    progress.visibility = View.GONE
                } else {
                    progress.visibility = View.VISIBLE
                }
            }
        }
        return view
    }

    private fun initViews(view: View) {
        vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        adapter = DrinkAdapter(requireContext(), click)
        progress = view.findViewById(R.id.progress)
        recyclerView = view.findViewById(R.id.iv_pager)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }


    private val click = object : DrinkAdapter.DrinkOnclick {
        override fun clickItem(id: Int) {
            val ldf = DetailFragment()
            val args = Bundle()
            args.putString("id", id.toString())
            ldf.arguments = args
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.nav_host_fragment, ldf)
                addToBackStack(null)
                commit()

            }
        }
    }

}
