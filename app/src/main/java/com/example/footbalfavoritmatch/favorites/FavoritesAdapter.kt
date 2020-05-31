package com.example.footbalfavoritmatch.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.db.Favorite
import java.util.zip.Inflater

class FavoritesAdapter(private val favorite: List<Favorite>, private val listener: (Favorite)->Unit):RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.list_match, parent, false)
        return FavoriteViewHolder(cellRow)
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

    }

}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view){

}