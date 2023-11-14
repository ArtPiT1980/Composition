package com.artpit.android.composition.domain.usecases

import com.artpit.android.composition.domain.entity.GameSettings
import com.artpit.android.composition.domain.entity.Level
import com.artpit.android.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}