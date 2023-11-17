package com.artpit.android.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.artpit.android.composition.R
import com.artpit.android.composition.databinding.FragmentChooseLevelFragmentBinding
import com.artpit.android.composition.domain.entity.Level

class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseLevelFragmentBinding? = null
    private val binding: FragmentChooseLevelFragmentBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelFragmentBinding == null")

    companion object {
        const val NAME = "ChooseLevelFragment"
        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonLevelTest.setOnClickListener {
                launchGameFragment(Level.TEST)
            }

            buttonLevelEasy.setOnClickListener {
                launchGameFragment(Level.EASY)
            }

            buttonLevelNormal.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }

            buttonLevelHard.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }
}