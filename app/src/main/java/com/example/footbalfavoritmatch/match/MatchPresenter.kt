package com.example.footbalfavoritmatch.match

import android.view.View
import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.api.TSDBApi
import com.example.footbalfavoritmatch.models.MatchModel
import com.example.footbalfavoritmatch.models.MatchResponse
import com.example.footbalfavoritmatch.search.SearchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView?,
                     private val api:ApiRepository,
                     private val gson: Gson) {

    fun getMatchList(idLeague: String){
        view?.showLoading()
        doAsync {
            val data = gson.fromJson(api.doRequest(TSDBApi.getMatch(idLeague)), MatchResponse::class.java)

            uiThread {
                view?.hideLoading()
                view?.showMatchList(data.events)
            }
        }
    }

    fun getNextMatchList(idLeague: String?){
        view?.showLoading()
        doAsync {

            val data = gson.fromJson(api.doRequest(TSDBApi.getNextMatch(idLeague)), MatchResponse::class.java)

            uiThread {
                view?.hideLoading()
                view?.showMatchList(data.events)
            }
        }
    }


    fun getSearchList(params: String?){
        view?.showLoading()
        doAsync {
            val data = gson.fromJson(api.doRequest(TSDBApi.getSearch(params)), SearchResponse::class.java)

            uiThread {
                view?.hideLoading()
                val value: List<MatchModel> = data.event.filter { it.strSport == "Soccer" }
                view?.showMatchList(value)
            }
        }
    }
}