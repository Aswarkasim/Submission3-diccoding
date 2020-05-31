package com.example.footbalfavoritmatch.match

import com.example.footbalfavoritmatch.models.MatchModel

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<MatchModel>)
}