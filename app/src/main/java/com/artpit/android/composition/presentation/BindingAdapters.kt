package com.artpit.android.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.artpit.android.composition.R
import com.artpit.android.composition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

//GameResult
@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, value: Boolean) {
    imageView.setImageResource(getSmileResId(value))
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(view: TextView, value: Int) {
    setFormattedValue(view, R.string.required_score, value)
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(view: TextView, value: Int) {
    setFormattedValue(view, R.string.required_percentage, value)
}

@BindingAdapter("countOfRightAnswers")
fun bindCountOfRightAnswers(view: TextView, value: Int) {
    setFormattedValue(view, R.string.score_answers, value)
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(view: TextView, value: GameResult) {
    setFormattedValue(view, R.string.score_percentage, getPercentageOfRightAnswers(value))
}

//GameFragment
@BindingAdapter("numberAsString")
fun bindNumberAsString(view: TextView, value: Int) {
    view.text = value.toString()
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(view: TextView, value: Boolean) {
    view.setTextColor(ColorStateList.valueOf(getColorByState(view.context, value)))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(view: ProgressBar, value: Boolean) {
    view.progressTintList = ColorStateList.valueOf(getColorByState(view.context, value))
}

fun <T>setFormattedValue(view: TextView, @StringRes resId: Int, value: T) {
    view.text = String.format(view.context.getString(resId), value)
}

private fun getColorByState(context: Context, state: Boolean): Int {
    val colorResId = if (state) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }

    return ContextCompat.getColor(context, colorResId)
}

private fun getSmileResId(value: Boolean): Int {
    return if (value) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

private fun getPercentageOfRightAnswers(value: GameResult) = with(value) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(view: TextView, clickListener: OnOptionClickListener) {
    view.setOnClickListener {
        clickListener.onOptionClick(view.text.toString().toInt())
    }
}