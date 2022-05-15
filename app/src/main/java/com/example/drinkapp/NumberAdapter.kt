package com.example.drinkapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.drinkapp.domain.model.CategoriesModel

class NumberAdapter(fragmentActivity: FragmentActivity, private val list : CategoriesModel) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 9

    override fun createFragment(position: Int): Fragment {
       val fragment = NumberFragment()
        fragment.arguments = Bundle().apply {
            putString(ARG_OBJECT,list.categoriesNameModels[position].strCategory)
        }
    return fragment
    }

}