package com.example.drinkapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.drinkapp.App
import com.example.drinkapp.R
import com.example.drinkapp.data.model.CategoriesDBModel
import com.example.drinkapp.presentation.vm.RoomViewModel
import com.example.drinkapp.presentation.vm.RoomViewModelFactory

class SettingsFragment : Fragment(), View.OnClickListener {

    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val cbOrdinary = view.findViewById<CheckBox>(R.id.cb_ordinary).setOnClickListener(this)
        val cbCocktail = view.findViewById<CheckBox>(R.id.cb_cocktail).setOnClickListener(this)
        val cbShake = view.findViewById<CheckBox>(R.id.cb_shake).setOnClickListener(this)
        val cbOther = view.findViewById<CheckBox>(R.id.cb_other).setOnClickListener(this)
        val cbCocoa = view.findViewById<CheckBox>(R.id.cb_cocoa).setOnClickListener(this)
        val cbShot = view.findViewById<CheckBox>(R.id.cb_shot).setOnClickListener(this)
        val cbCoffee = view.findViewById<CheckBox>(R.id.cb_coffee).setOnClickListener(this)
        val cbHomemade = view.findViewById<CheckBox>(R.id.cb_homemade).setOnClickListener(this)
        val cbPunch = view.findViewById<CheckBox>(R.id.cb_punch).setOnClickListener(this)
        val cbBeer = view.findViewById<CheckBox>(R.id.cb_beer).setOnClickListener(this)
        val cbSoft = view.findViewById<CheckBox>(R.id.cb_soft).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cb_ordinary -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=287 ,strCategory = "Ordinary Drink" , status = true))
                Toast.makeText(requireContext(), "cb_ordinary", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_cocktail -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=288 ,strCategory = "Сocktail" , status = true))
                Toast.makeText(requireContext(), "cb_cocktail", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_shake -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=289 ,strCategory = "Shake" , status = true))
                Toast.makeText(requireContext(), "cb_shake", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_other -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=290 ,strCategory = "Other/Unknown" , status = true))
                Toast.makeText(requireContext(), "cb_other", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_cocoa -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=291 ,strCategory = "Cocoa" , status = true))
                Toast.makeText(requireContext(), "cb_cocoa", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_shot -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=292 ,strCategory = "Shot" , status = true))
                Toast.makeText(requireContext(), "cb_shot", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_coffee -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=293 ,strCategory = "Coffee/ Tea" , status = true))
                Toast.makeText(requireContext(), "cb_coffee", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_punch -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=295 ,strCategory = "Punch/ Party Drink" , status = true))
                Toast.makeText(requireContext(), "cb_punch", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_beer -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=296 ,strCategory = "Beer" , status = true))

                Toast.makeText(requireContext(), "cb_beer", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_soft -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=297 ,strCategory = "Beer" , status = true))

                Toast.makeText(requireContext(), "cb_soft", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_homemade -> {
                roomViewModel.updateCategory(CategoriesDBModel(id=294 ,strCategory = "Homemade Liqueur" , status = true))
                Toast.makeText(requireContext(), "cb_homemade", Toast.LENGTH_SHORT).show()
            }
        }

    }

}