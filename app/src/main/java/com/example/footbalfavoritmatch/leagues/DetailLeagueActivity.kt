package com.example.footbalfavoritmatch.leagues

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.models.LeagueDetail
import com.example.footbalfavoritmatch.models.LeaguesModel
import com.example.footbalfavoritmatch.utils.invisible
import com.example.footbalfavoritmatch.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailLeagueActivity : AppCompatActivity(), LeagueDetailView {

    private lateinit var leagueBadge: ImageView
    private lateinit var leagueName: TextView
    private lateinit var leagueDesc: TextView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: LeagueDetailPresenter

    private val leagues: MutableList<LeagueDetail> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idLeague = intent.getStringExtra("idLeague")
        supportActionBar?.title = "Detail League"

        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL

            swipeRefreshLayout = swipeRefreshLayout{
                setColorSchemeResources(android.R.color.holo_red_light)

                scrollView {
                    isVerticalScrollBarEnabled = false


                    relativeLayout {
                        lparams(matchParent, wrapContent)
                        linearLayout {
                            lparams(matchParent, wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL

                            leagueBadge = imageView{}.lparams(height = dip(75))

                            leagueName = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 30f
                                text = "League Namme"
                            }.lparams{topMargin = dip(5)}

                            leagueDesc = textView().lparams{
                                topMargin = dip(20)
                            }
                        }
                        progressBar = progressBar {  }.lparams { centerHorizontally() }
                    }
                }
            }
        }

        presenter = LeagueDetailPresenter(this, ApiRepository(), Gson())
        presenter.getDetail(idLeague)

        swipeRefreshLayout.onRefresh {
            presenter.getDetail(idLeague)
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetailLeague(data: List<LeagueDetail>) {
        supportActionBar?.title = data[0].strLeague
        swipeRefreshLayout.isRefreshing = false
        Picasso.get().load(data[0].strBadge).into(leagueBadge)
        leagueName.text = data[0].strLeague
        leagueDesc.text = data[0].strDescriptionEN
    }

}
