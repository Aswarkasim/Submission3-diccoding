package com.example.footbalfavoritmatch.match

import android.content.Context
import android.os.Bundle
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
import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.detail.DetailMatchActivity
import com.example.footbalfavoritmatch.models.MatchModel
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
class LastMatchFragment(val idLeague: String?) : Fragment(), MatchView {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var listMatch: RecyclerView

    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter

    private val events: MutableList<MatchModel> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        adapter = MatchAdapter(events){
            //toast("adakah ${it.idAwayTeam} dan ${it.strAwayTeam}")
            startActivity<DetailMatchActivity>(DetailMatchActivity.EXTRA_DATA to it)
        }
        listMatch.adapter = adapter

        presenter = MatchPresenter(this, ApiRepository(), Gson())

        if (idLeague != null) {
            swipeRefreshLayout.onRefresh {
                presenter.getMatchList(idLeague)
            }
        }


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }


    private fun createView(ui:AnkoContext<Context>): View = with(ui){
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

                    listMatch = recyclerView{
                        lparams(matchParent, wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar{}.lparams{centerHorizontally()}
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

    override fun showMatchList(data: List<MatchModel>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
