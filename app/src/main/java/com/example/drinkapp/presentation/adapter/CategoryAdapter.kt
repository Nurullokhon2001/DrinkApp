package com.example.drinkapp.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.presentation.ui.ARG_OBJECT
import com.example.drinkapp.presentation.ui.CategoryFragment

class CategoryAdapter(fragmentActivity: FragmentActivity, private val list : CategoriesModel) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 9

    override fun createFragment(position: Int): Fragment {
       val fragment = CategoryFragment()
        fragment.arguments = Bundle().apply {
            putString(ARG_OBJECT,list.categoriesNameModels[position].strCategory)
        }
    return fragment
    }

}