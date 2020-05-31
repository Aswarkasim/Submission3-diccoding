package com.example.footbalfavoritmatch.leagues

import android.util.Log
import com.example.footbalfavoritmatch.api.ApiRepository
import com.example.footbalfavoritmatch.api.TSDBApi
import com.example.footbalfavoritmatch.models.LeagueResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguesPresenter (private val view: LeaguesFragment,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson){

    fun getLeagueList(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TSDBApi.getLeagues()), LeagueResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showLeaguesLoading(data.leagues)
                Log.d("Adakah", "ini")
            }
        }
    }
}