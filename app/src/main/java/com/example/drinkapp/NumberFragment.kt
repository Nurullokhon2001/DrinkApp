package com.example.drinkapp
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.DrinkAdapter
import com.example.drinkapp.presentation.vm.CategoriesVm


const val ARG_OBJECT = "object"

class NumberFragment : Fragment() {
    private lateinit var progress: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        progress = view.findViewById(R.id.progress)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            vm.getDrinks(getString(ARG_OBJECT).toString())
        }
        val adapter = DrinkAdapter(requireContext(), click)

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

//            val ldf = DetailFragment()
//            val args = Bundle()
//            args.putString("id", id.toString())
//            ldf.arguments = args
////            requireActivity().supportFragmentManager.beginTransaction()
////                .replace(R.id.nav_host_fragment, ldf).addToBackStack(null).commit()
//
//            val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
//            ft.replace(R.id.nav_host_fragment, ldf ).addToBackStack(null)
//            ft.commit()'

            val action = NumberFragmentDirections.actionCategoryToDetailFragment(id)
            view?.let { Navigation.findNavController(it).navigate(action) }

            Toast.makeText(requireContext(), "$id", Toast.LENGTH_SHORT).show()

        }
    }

}