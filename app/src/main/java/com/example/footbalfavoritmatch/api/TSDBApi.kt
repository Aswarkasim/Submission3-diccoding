package com.example.footbalfavoritmatch.api

import com.example.footbalfavoritmatch.BuildConfig

object TSDBApi {
    fun getLeagues(): String {
        return BuildConfig.BASE_URL+BuildConfig.TSDB_API_KEY+"all_leagues.php"
    }

    fun getMatch(idLeague: String?): String{
        return BuildConfig.BASE_URL+BuildConfig.TSDB_API_KEY+"eventspastleague.php?id=$idLeague"
    }

    fun getNextMatch(idLeague: String?): String {
        return BuildConfig.BASE_URL+BuildConfig.TSDB_API_KEY+"eventsnextleague.php?id=$idLeague"
    }

    fun getSearch(params: String?): String{
        return BuildConfig.BASE_URL+BuildConfig.TSDB_API_KEY+"searchevents.php?e=$params"
    }

    fun getTeam(idTeam: String?): String{
        return BuildConfig.BASE_URL+BuildConfig.TSDB_API_KEY+"lookupteam.php?id=$idTeam"
    }
}