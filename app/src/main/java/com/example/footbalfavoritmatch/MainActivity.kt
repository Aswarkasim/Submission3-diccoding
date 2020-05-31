package com.example.footbalfavoritmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.footbalfavoritmatch.favorites.FavoritesFragment
import com.example.footbalfavoritmatch.leagues.LeaguesFragment
import com.example.footbalfavoritmatch.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.leagues_menu->{
                    loadLeaguesFragment(savedInstanceState)
                }

                R.id.search_bottom_menu->{
                    loadSearchFragment(savedInstanceState)
                }

                R.id.favorites_menu->{
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.leagues_menu
    }

    private fun loadLeaguesFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LeaguesFragment(), LeaguesFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadSearchFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, SearchFragment(), SearchFragment::class.java.simpleName)
                .commit()
        }
    }
}
