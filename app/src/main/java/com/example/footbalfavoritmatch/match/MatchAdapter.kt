package com.example.footbalfavoritmatch.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalfavoritmatch.R
import com.example.footbalfavoritmatch.models.MatchModel

class MatchAdapter(private val events: List<MatchModel>, private val listener:(MatchModel)->Unit): RecyclerView.Adapter<MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow =  layoutInflater.inflate(R.layout.list_match, parent, false)
        return MatchViewHolder(cellRow)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        return holder.bindItem(events[position], listener)
    }

}

class MatchViewHolder(View: View):RecyclerView.ViewHolder(View){

    private val homeTeam: TextView = View.findViewById(R.id.textView_HomeTeam_LastMatch)
    private val homeScore: TextView = View.findViewById(R.id.textView_home_score_match)

    private val awayTeam: TextView = View.findViewById(R.id.textView_away_team_last_match)
    private val awayScore: TextView = View.findViewById(R.id.textView_away_score_match)

    private val date: TextView = View.findViewById(R.id.textView_date_last_match)

    fun bindItem(events: MatchModel, listener: (MatchModel) -> Unit){
        homeTeam.text = events.strHomeTeam
        homeScore.text = events.intHomeScore

        awayTeam.text = events.strAwayTeam
        awayScore.text = events.intHomeScore

        date.text = events.dateEvent

        itemView.setOnClickListener {
            listener(events)
        }
    }
}