package com.example.footbalfavoritmatch.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MatchModel(
    var idEvent: String? = null,
    var idLeague: String? = null,
    val strSport:String? = null,

    var strHomeTeam: String? = null,
    var strAwayTeam: String? = null,

    var intHomeScore: String? = null,
    var intAwayScore: String? = null,

    var idHomeTeam: String? = null,
    var idAwayTeam: String? = null,

    var dateEvent: String? = null,
    var strTime: String? = null,

    val strHomeLineUpGoalkeeper: String? = null,
    val strAwayLineUpGoalkeeper: String? = null,

    val strHomeLineUpDefense: String? = null,
    val strAwayLineUpDefense: String? = null,

    val strHomeLineUpMinfield: String? = null,
    val strAwayLineUpMinfield: String? = null,

    val strHomeLineUpForward: String? = null,
    val strAwayLineUpForward: String? = null,

    val strHomeLineUpSubstitutes: String? = null,
    val strAwayLineUpSubstitutes: String? = null
):Parcelable