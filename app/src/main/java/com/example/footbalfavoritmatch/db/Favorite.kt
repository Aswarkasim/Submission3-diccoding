package com.example.footbalfavoritmatch.db

data class Favorite(
    val id: Long?,
    val idEvent: String?,

    val date: String?,

    val idHomeTeam: String?,
    val idAwayTeam: String?,

    val HomeTeam: String?,
    val AwayTeam: String?,

    val homeScore: String?,
    val awayScore: String?
){
    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID = "EVENT_ID"
        const val DATE: String = "DATE"



        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"

        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"

        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}