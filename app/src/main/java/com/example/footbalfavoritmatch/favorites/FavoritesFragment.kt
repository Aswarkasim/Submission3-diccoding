package com.example.footbalfavoritmatch.favorites

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.db.Favorite
import com.example.footbalfavoritmatch.db.database
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    private lateinit var adapter: FavoritesAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var listFavorite: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = FavoritesAdapter(favorites){
            toast("Adakah ${it.HomeTeam}")
        }
        listFavorite.adapter = adapter

        swipeRefreshLayout.onRefresh {
            super.onResume()
            showFavorite()
        }

    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            swipeRefreshLayout.isRefreshing =  false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            Log.d("Adakah", "ada $favorite")
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.create(requireContext()))
    }

    private fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)


            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent)
                relativeLayout {
                    lparams(matchParent, wrapContent)

                    listFavorite = recyclerView{
                        lparams(matchParent, wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                   // progressBar = progressBar{}.lparams{centerHorizontally()}
                }
            }
        }
    }

}
