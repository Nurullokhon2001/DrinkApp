package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.CategoriesNameModel
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.presentation.adapter.DrinkAdapter

class ViewPagerAdapter(val array: List<CategoriesNameModel>, val contextt: Context) :
    RecyclerView.Adapter<PagerVH>() {
    private var array2: ArrayList<Drink> = ArrayList()
    val adapter = DrinkAdapter(contextt)
    private val images = arrayListOf<Drink>(
        Drink("id", "name", "icon"),
        Drink("id", "name", "icon"),
        Drink("id", "name", "icon"),
        Drink("id", "name", "icon"),
        Drink("id", "name", "icon"),
        Drink("id", "name", "icon"),
        Drink("id", "name", "icon"),
        Drink("id", "name", "icon"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(
            LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        )

    override fun getItemCount(): Int = array.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {

        holder.image.adapter = adapter
        holder.image.layoutManager = GridLayoutManager(context, 2)

    }
    fun setData(array: ArrayList<Drink>) {
        this.array2 = array
        adapter.setData(array)
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: RecyclerView = itemView.findViewById(R.id.iv_pager)
}