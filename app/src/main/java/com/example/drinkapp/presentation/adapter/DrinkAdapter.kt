package com.example.drinkapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Drink

class DrinkAdapter(private val context: Context) :
    RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {
    private var array: ArrayList<Drink> = ArrayList()

    class DrinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = itemView.findViewById(R.id.iv_drink)
        val tvName: TextView = itemView.findViewById(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drink_item, parent, false)

        return DrinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        Glide
            .with(context)
            .load(array[position].strDrinkThumb)
            .centerCrop()
            .into(holder.image)
        holder.tvName.text = array[position].strDrink
    }

    override fun getItemCount() = array.size

    fun setData(array: ArrayList<Drink>) {
        this.array = array
        notifyDataSetChanged()
    }
}