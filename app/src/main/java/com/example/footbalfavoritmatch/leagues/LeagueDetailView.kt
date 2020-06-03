package com.example.footbalfavoritmatch.leagues

import com.example.footbalfavoritmatch.models.LeagueDetail

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailLeague(data: List<LeagueDetail>)
}