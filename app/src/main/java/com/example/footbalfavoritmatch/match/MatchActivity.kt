package com.example.footbalfavoritmatch.match

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.footbalfavoritmatch.R
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {


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
