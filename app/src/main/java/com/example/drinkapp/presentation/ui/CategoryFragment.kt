package com.example.drinkapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.App
import com.example.drinkapp.R
import com.example.drinkapp.data.mappers.Mappers
import com.example.drinkapp.data.model.HistoryModel
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.DrinkAdapter
import com.example.drinkapp.presentation.vm.CategoriesVm
import com.example.drinkapp.presentation.vm.HistoryVM
import com.example.drinkapp.presentation.vm.HistoryViewModelFactory


const val ARG_OBJECT = "object"

class CategoryFragment : Fragment() {

    private lateinit var progress: ProgressBar
    private lateinit var adapter: DrinkAdapter
    private lateinit var vm: CategoriesVm
    private lateinit var recyclerView: RecyclerView

    private val historyVm: HistoryVM by viewModels {
        HistoryViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //  setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            vm.getDrinks(getString(ARG_OBJECT).toString())
        }


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

    private fun initViews(view: View) {
        vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        adapter = DrinkAdapter(requireContext(), click)
        progress = view.findViewById(R.id.progress)
        recyclerView = view.findViewById(R.id.iv_pager)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }

    private val click = object : DrinkAdapter.DrinkOnclick {
        override fun clickItem(id: Drink) {
//            val ldf = DetailFragment()
//            val args = Bundle()
//            args.putString("id", id.toString())
//            ldf.arguments = args
//            requireActivity().supportFragmentManager.beginTransaction().apply {
//                add(R.id.nav_host_fragment, ldf)
//                addToBackStack(null)
//                commit()

            historyVm.insertHistory(Mappers.mapDrinkToHistoryModel(id))

        }
    }
}
