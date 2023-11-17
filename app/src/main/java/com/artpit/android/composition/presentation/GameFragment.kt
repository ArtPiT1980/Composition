package com.artpit.android.composition.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.artpit.android.composition.R
import com.artpit.android.composition.databinding.FragmentGameBinding
import com.artpit.android.composition.domain.entity.GameResult
import com.artpit.android.composition.domain.entity.GameSettings
import com.artpit.android.composition.domain.entity.Level


class GameFragment : Fragment() {
    private lateinit var level: Level
    private lateinit var gameSettings: GameSettings
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    companion object {
        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"
        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSum.setOnClickListener {
            launchGameFinishedFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        level = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(KEY_LEVEL, Level::class.java) as Level
        } else {
            requireArguments().getSerializable(KEY_LEVEL) as Level
        }

        gameSettings = GameSettings(
            maxSumValue = 50,
            minCountOfRightAnswers = 50,
            minPercentOfRightAnswers = 50,
            gameTimeInSeconds = 60
        )
    }

    private fun launchGameFinishedFragment() {
        val gameResult = GameResult(
            winner = true,
            countOfRightAnswers = 50,
            countOfQuestions = 50,
            gameSettings = gameSettings
        )

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack("")
            .commit()
    }
}