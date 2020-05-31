package com.example.footbalfavoritmatch.detail

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.db.Favorite
import com.example.footbalfavoritmatch.db.database
import com.example.footbalfavoritmatch.models.MatchModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.Android
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(), DetailView {

    companion object{
        val EXTRA_DATA = "extra_data"
    }
    private var menuItem: Menu? = null
    private var isFavorite: Boolean =  false
    private lateinit var event: MatchModel
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var presenter:DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)


        val detailMatch = intent.getParcelableExtra<MatchModel>(EXTRA_DATA)

        textView_Home_Team_detail.text = detailMatch.strHomeTeam
        textView_Away_Team_detail.text = detailMatch.strAwayTeam

        textView_away_score_detail.text = detailMatch.intHomeScore
        textView_home_score_detail.text = detailMatch.intAwayScore

        textView_home_defense_detail.text = detailMatch.strHomeLineUpDefense
        textView_home_forward_detail.text = detailMatch.strHomeLineUpForward
        textView_home_goal_keeper_detail.text = detailMatch.strHomeLineUpGoalkeeper
        textView_home_minifield_detail.text = detailMatch.strHomeLineUpMinfield
        textView_home_substitutes_detail.text = detailMatch.strHomeLineUpSubstitutes


        textView_away_substitutes_detail.text = detailMatch.strAwayLineUpSubstitutes
        textView_away_forward_detail.text = detailMatch.strAwayLineUpForward
        textView_away_goal_keeper_detail.text = detailMatch.strHomeLineUpGoalkeeper
        textView_away_minifield_detail.text = detailMatch.strAwayLineUpMinfield
        textView_away_defense_detail.text = detailMatch.strAwayLineUpDefense

        presenter = DetailPresenter(this, ApiRepository(), Gson())
        presenter.getTeamDetail(detailMatch.idHomeTeam.toString(), detailMatch.idAwayTeam.toString())



    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            R.id.add_to_favorite -> {
                addToFavorite()
                true
            }
            else ->  super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        val detailMatch = intent.getParcelableExtra<MatchModel>(EXTRA_DATA)
        Log.d("Adakah", "ada ini $detailMatch")
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to detailMatch.idEvent,

                    Favorite.ID_HOME_TEAM to detailMatch.idHomeTeam,
                    Favorite.ID_AWAY_TEAM to detailMatch.idAwayTeam,

                    Favorite.HOME_TEAM to detailMatch.strHomeTeam,
                    Favorite.AWAY_TEAM to detailMatch.strAwayTeam,

                    Favorite.HOME_SCORE to detailMatch.intHomeScore,
                    Favorite.AWAY_SCORE to detailMatch.intAwayScore,

                    Favorite.DATE to detailMatch.dateEvent
                )
            }
            //swipeRefresh.snackbar("Addded to Favorite").show()

            toast("Favorite ditambah")
        }catch (e: SQLiteConstraintException){
            //swipeRefresh.snackbar(e.localizedMessage).show()
            toast(e.localizedMessage).show()
        }
    }

    override fun showTeamDetails(homeBadge: String?, awayBadge: String?) {

        val homeImage = findViewById<ImageView>(R.id.imageView_logo_team_home_detail)
        val awayImage = findViewById<ImageView>(R.id.imageView_logo_team_away_detail)

        Picasso.get().load(homeBadge).fit().into(homeImage)
        Picasso.get().load(awayBadge).fit().into(awayImage)
    }
}
