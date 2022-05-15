package com.example.drinkapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.DrinkAdapter
import com.example.drinkapp.presentation.ui.DetailFragment
import com.example.drinkapp.presentation.vm.CategoriesVm


const val ARG_OBJECT = "object"

class NumberFragment : Fragment() {
    private lateinit var progress: ProgressBar
    lateinit var adapter: DrinkAdapter
    lateinit var vm: CategoriesVm
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_number, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        progress = view.findViewById(R.id.progress)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            vm.getDrinks(getString(ARG_OBJECT).toString())
        }
         adapter = DrinkAdapter(requireContext(), click)

        val recyclerview: RecyclerView = view.findViewById(R.id.iv_pager)
        recyclerview.layoutManager = GridLayoutManager(context, 2)
        recyclerview.adapter = adapter

        vm.drinks.observe(viewLifecycleOwner) {
            Log.e("onCreateView", "onCreateView: ${it.body()!!.drinks.size}")
            adapter.setData(it.body()!!.drinks as ArrayList<Drink>)
        }

        vm.isLoading.observe(viewLifecycleOwner) {
            if (it) {

                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        }


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

                Toast.makeText(requireContext(), "$id", Toast.LENGTH_SHORT).show()

            }
        }
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
                    vm.getDrinksByName(newText).observe(viewLifecycleOwner) {
                        Log.e("onCreateView", "onCreateView: ${it.body()!!.drinks.size}")
                        adapter.setData(it.body()!!.drinks.map {
                            Drink(
                                it.idDrink,
                                it.strDrink,
                                it.strDrinkThumb
                            )
                        } as ArrayList<Drink>)
                    }
//                    tabLayout.visibility = View.GONE
                } else {
//                    tabLayout.visibility = View.VISIBLE
                }
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}