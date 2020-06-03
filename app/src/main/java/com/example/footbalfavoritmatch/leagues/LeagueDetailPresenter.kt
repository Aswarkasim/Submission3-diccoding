package com.example.footbalfavoritmatch.leagues

import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.api.TSDBApi
import com.example.footbalfavoritmatch.models.LeagueResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeagueDetailPresenter (private val view: LeagueDetailView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson
){
    fun getDetail(idLeague: String?){
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TSDBApi.getLeagueDetail(idLeague)), LeaguesDetailResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showDetailLeague(data.leagues)
            }
        }
    }
}