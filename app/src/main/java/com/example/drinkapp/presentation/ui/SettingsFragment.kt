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
        var cbOrdinary = view.findViewById<CheckBox>(R.id.cb_ordinary)
        cbOrdinary.setOnClickListener(this)

        var cbCocktail = view.findViewById<CheckBox>(R.id.cb_cocktail)
        cbCocktail.setOnClickListener(this)

        var cbShake = view.findViewById<CheckBox>(R.id.cb_shake)
        cbShake.setOnClickListener(this)

        var cbOther = view.findViewById<CheckBox>(R.id.cb_other)
        cbOther.setOnClickListener(this)

        var cbCocoa = view.findViewById<CheckBox>(R.id.cb_cocoa)
        cbCocoa.setOnClickListener(this)

        var cbShot = view.findViewById<CheckBox>(R.id.cb_shot)
        cbShot.setOnClickListener(this)

        var cbCoffee = view.findViewById<CheckBox>(R.id.cb_coffee)
        cbCoffee.setOnClickListener(this)

        var cbHomemade = view.findViewById<CheckBox>(R.id.cb_homemade)
        cbHomemade.setOnClickListener(this)

        var cbPunch = view.findViewById<CheckBox>(R.id.cb_punch)
        cbPunch.setOnClickListener(this)

        var cbBeer = view.findViewById<CheckBox>(R.id.cb_beer)
        cbBeer.setOnClickListener(this)

        var cbSoft = view.findViewById<CheckBox>(R.id.cb_soft)
        cbSoft.setOnClickListener(this)

        roomViewModel.getCategories.observe(viewLifecycleOwner) {
            cbOrdinary.isChecked = true
        }

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cb_ordinary -> {
                roomViewModel.updateCategory(false, "Ordinary Drink")
                Toast.makeText(requireContext(), "cb_ordinary", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_cocktail -> {
                roomViewModel.updateCategory(false, "Cocktail")
                Toast.makeText(requireContext(), "cb_cocktail", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_shake -> {
                roomViewModel.updateCategory(false, "Shake")

                Toast.makeText(requireContext(), "cb_shake", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_other -> {
                roomViewModel.updateCategory(false, "Other/Unknown")
                Toast.makeText(requireContext(), "cb_other", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_cocoa -> {
                roomViewModel.updateCategory(false, "Cocoa")
                Toast.makeText(requireContext(), "cb_cocoa", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_shot -> {
                roomViewModel.updateCategory(false, "Shot")
                Toast.makeText(requireContext(), "cb_shot", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_coffee -> {
                roomViewModel.updateCategory(false, "Coffee / Tea")
                Toast.makeText(requireContext(), "cb_coffee", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_punch -> {
                roomViewModel.updateCategory(false, "Punch / Party Drink")
                Toast.makeText(requireContext(), "cb_punch", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_beer -> {
                roomViewModel.updateCategory(false, "Beer")

                Toast.makeText(requireContext(), "cb_beer", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_soft -> {
                roomViewModel.updateCategory(false, "Soft Drink")
                Toast.makeText(requireContext(), "cb_soft", Toast.LENGTH_SHORT).show()
            }
            R.id.cb_homemade -> {
                roomViewModel.updateCategory(false, "Homemade Liqueur")
                Toast.makeText(requireContext(), "cb_homemade", Toast.LENGTH_SHORT).show()
            }
        }

    }

}