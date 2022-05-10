package com.example.drinkapp.presentation.ui

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.drinkapp.R
import com.example.drinkapp.presentation.vm.CategoriesVm
import org.w3c.dom.Text

class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val vm = ViewModelProvider(this).get(CategoriesVm::class.java)
        val tvDetail = view.findViewById<TextView>(R.id.tv_drink_detail)
        val image = view.findViewById<ImageView>(R.id.iv_image)
        val tvIngredient = view.findViewById<TextView>(R.id.tv_ingredient_detail)
        val progress = view.findViewById<ProgressBar>(R.id.progress)
        val id = args.id.toString()
        vm.getDetailsDrinkById(id).observe(viewLifecycleOwner) { it ->
            it.body()?.let {
                val details = it.drinks[0]
                tvDetail.text = "DateModified : ${details.dateModified} \n" +
                        "idDrink : ${details.idDrink} \n" +
                        "strAlcoholic : ${details.strAlcoholic} \n" +
                        "strCategory : ${details.strCategory} \n" +
                        "strCreativeCommonsConfirmed : ${details.strCreativeCommonsConfirmed} \n" +
                        "strDrink : ${details.strDrink} \n" +
                        "strDrinkAlternate : ${details.strDrinkAlternate} \n" +
                        "strDrinkThumb : ${details.strDrinkThumb} \n" +
                        "strGlass : ${details.strGlass} \n" +
                        "strImageSource : ${details.strImageSource} \n" +
                        "strIngredient1 : ${details.strIngredient1} \n" +
                        "strIngredient2 : ${details.strIngredient2} \n" +
                        "strIngredient3 : ${details.strIngredient3} \n" +
                        "strIngredient4 : ${details.strIngredient4} \n" +
                        "strInstructionsDE : ${details.strInstructionsDE} \n" +
                        "strInstructions : ${details.strInstructions} \n" +
                        "strMeasure3 : ${details.strMeasure3} \n" +
                        "strMeasure2 : ${details.strMeasure2} \n" +
                        "strImageAttribution : ${details.strImageAttribution} \n"

                Glide
                    .with(requireContext())
                    .load(details.strDrinkThumb)
                    .centerCrop()
                    .into(image)
            }

            vm.isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    progress.visibility = View.VISIBLE
                } else {
                    progress.visibility = View.GONE
                }
            }

//            vm.getIngredientById(id).observe(viewLifecycleOwner) { ingredient ->
//                ingredient.body()?.let {
//                    val ingredient = it.ingredients[0]
//                    ingredient.let {
//                        tvIngredient.text = "strABV : ${ingredient.strABV}" +
//                                " strAlcohol: ${ingredient.strAlcohol}" +
//                                "strDescription : ${ingredient.strDescription}" +
//                                "strIngredient : ${ingredient.strIngredient}" +
//                                "strType : ${ingredient.strType}"
//                    }
//                }
//            }

        }

        return view

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().popBackStack()
            return true
        }
        return true
    }

}