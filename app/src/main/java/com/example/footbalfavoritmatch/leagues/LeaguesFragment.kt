package com.example.footbalfavoritmatch.leagues

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.footbalfavoritmatch.BuildConfig
import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.match.MatchActivity
import com.example.footbalfavoritmatch.models.LeaguesModel
import com.example.footbalfavoritmatch.utils.invisible
import com.example.footbalfavoritmatch.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class LeaguesFragment : Fragment(), LeaguesView {


    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var listLeagues: RecyclerView

    private lateinit var adapter: LeaguesAdapter
    private lateinit var presenter: LeaguesPresenter

    private var leagues: MutableList<LeaguesModel> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        adapter = LeaguesAdapter(leagues){
            startActivity<MatchActivity>("idLeague" to it.idLeague)
        }

       // adapter = LeaguesAdapter()

        listLeagues.adapter = adapter

        presenter = LeaguesPresenter(this, ApiRepository(), Gson())
        presenter.getLeagueList()

        swipeRefresh.onRefresh {
            presenter.getLeagueList()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(matchParent, wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)


            relativeLayout {
                lparams(matchParent, wrapContent)
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(R.color.colorAccent)

                    listLeagues = recyclerView{
                        id = R.id.list_league
                        lparams(matchParent, wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar()
                }
            }

        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeaguesLoading(data: List<LeaguesModel>) {
        swipeRefresh.isRefreshing = false
        leagues.clear()
        leagues.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
