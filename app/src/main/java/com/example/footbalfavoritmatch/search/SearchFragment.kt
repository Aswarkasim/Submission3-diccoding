package com.example.footbalfavoritmatch.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.detail.DetailMatchActivity
import com.example.footbalfavoritmatch.match.MatchAdapter
import com.example.footbalfavoritmatch.match.MatchPresenter
import com.example.footbalfavoritmatch.match.MatchView
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
class SearchFragment : Fragment(), MatchView {

    private lateinit var editText_search: EditText
    private lateinit var listSearch: RecyclerView
    private lateinit var buttonSearch: Button
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter

    private val event: MutableList<MatchModel> = mutableListOf()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonSearch.setOnClickListener {
            val params = editText_search.text.toString()

            adapter = MatchAdapter(event){
                //toast("adakah")
                startActivity<DetailMatchActivity>(DetailMatchActivity.EXTRA_DATA to it)
            }

            listSearch.adapter = adapter
            presenter = MatchPresenter(this, ApiRepository(), Gson())

                presenter.getSearchList(params)


        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            rightPadding = dip(16)
            leftPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {

                    linearLayout {
                        lparams(matchParent, wrapContent)
                        orientation = LinearLayout.VERTICAL
                        editText_search = editText {
                            hint = "Masukkan Nama Team"
                        }.lparams(matchParent, wrapContent)

                        buttonSearch = button {
                            text = "Search"
                        }.lparams(matchParent, wrapContent)

                        listSearch = recyclerView {
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(matchParent, wrapContent)

                        progressBar = progressBar {

                        }
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
        swipeRefresh.isRefreshing = false
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
