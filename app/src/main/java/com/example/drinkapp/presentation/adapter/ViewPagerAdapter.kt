package com.example.drinkapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.CategoriesNameModel
import com.example.drinkapp.domain.model.Drink

class ViewPagerAdapter(private val array: List<CategoriesNameModel>, context: Context, mItemClickListener: DrinkAdapter.DrinkOnclick) :
    RecyclerView.Adapter<PagerVH>() {
    private var array2: ArrayList<Drink> = ArrayList()
    private val adapter = DrinkAdapter(context,mItemClickListener)

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