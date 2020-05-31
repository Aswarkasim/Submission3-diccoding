package com.example.footbalfavoritmatch.leagues

import com.example.footbalfavoritmatch.models.LeaguesModel

interface LeaguesView {
    fun showLoading()
    fun hideLoading()
    fun showLeaguesLoading(data: List<LeaguesModel>)
}