package com.example.footbalfavoritmatch.leagues

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.models.LeaguesModel
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LeaguesAdapter(private val leagues: List<LeaguesModel>, private val listener: (LeaguesModel)->Unit): RecyclerView.Adapter<LeaguesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesViewHolder {
        return LeaguesViewHolder(
            LeagueListUI().createView(AnkoContext.create(parent.context, parent))
        )
    }

    override fun getItemCount(): Int {
        return leagues.size
    }

    override fun onBindViewHolder(holder: LeaguesViewHolder, position: Int) {
        return holder.bindItem(leagues[position],listener)
    }

}

class LeaguesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val leagueName = view.findViewById<TextView>(R.id.league_name)
    fun bindItem(leagues: LeaguesModel, listener: (LeaguesModel) -> Unit){
        leagueName.text = leagues.strLeague

        itemView.setOnClickListener {
            listener(leagues)
        }
    }

}

class LeagueListUI: AnkoComponent<ViewGroup>{
    @SuppressLint("ResourceAsColor")
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){

                linearLayout {
                    lparams(matchParent, wrapContent)
                    padding = dip(5)
                    orientation = LinearLayout.VERTICAL

                    cardView {
                        lparams(matchParent, wrapContent)

                        textView {
                            id = R.id.league_name
                            text = "adakah"
                        }.lparams {
                            margin = dip(24)
                        }
                }

            }
        }
    }
}
