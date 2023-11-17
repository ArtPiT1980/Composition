package com.artpit.android.composition.domain.entity

import java.io.Serializable

data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
) : Serializable {
    val countOfRightAnswersString: String
        get() = countOfRightAnswers.toString()
}