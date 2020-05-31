package com.example.footbalfavoritmatch.detail

import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.api.TSDBApi
import com.example.footbalfavoritmatch.models.DetailTeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson){

    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String){
        async {
            val dataHomeTeam = gson.fromJson(apiRepository
                .doRequest(TSDBApi.getTeam(idHomeTeam)), DetailTeamResponse::class.java)

            val dataAway = gson.fromJson(apiRepository
                .doRequest(TSDBApi.getTeam(idAwayTeam)), DetailTeamResponse::class.java)

            uiThread {
                view.showTeamDetails(dataHomeTeam.teams[0].strTeamBadge!!, dataAway.teams[0].strTeamBadge!!)
            }
        }
    }
}