package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkapp.R
import com.example.drinkapp.model.Drink

class ViewPagerAdapter(val array : List<Drink>) : RecyclerView.Adapter<PagerVH>() {

    private val images = intArrayOf(
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category,
        R.drawable.ic_category
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(
            LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        )

    override fun getItemCount(): Int = array.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        holder.image.setImageResource(images[position])
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView = itemView.findViewById(R.id.iv_pager)
}