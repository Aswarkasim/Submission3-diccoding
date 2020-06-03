package com.example.footbalfavoritmatch.match

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.leagues.DetailLeagueActivity
import kotlinx.android.synthetic.main.activity_match.*
import org.jetbrains.anko.startActivity

class MatchActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val idLeague = intent.getStringExtra("idLeague")

        val adapter = MatchViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LastMatchFragment(idLeague), "Last Fragment")
        adapter.addFragment(NextMatchFragment(idLeague), "Next Fragment")
        ViewPagerMatch.adapter = adapter
        tabMatch.setupWithViewPager(ViewPagerMatch)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.info_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val idLeague = intent.getStringExtra("idLeague")
        when(item?.itemId){
            R.id.button_info_menu ->{
                startActivity<DetailLeagueActivity>("idLeague" to idLeague)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    class MatchViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){

        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }

}
