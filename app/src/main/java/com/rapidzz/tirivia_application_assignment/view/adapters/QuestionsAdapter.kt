package com.rapidzz.tirivia_application_assignment.view.adapters

import android.view.View
import android.widget.RadioButton
import com.rapidzz.tirivia_application_assignment.R
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.ResultModel
import com.rapidzz.tirivia_application_assignment.utils.application.gone
import com.rapidzz.tirivia_application_assignment.utils.application.visible
import kotlinx.android.synthetic.main.rv_questions.view.*

class QuestionsAdapter(
    var items: ArrayList<ResultModel>,
    var onClicker: OnItemClicker
) :
    BaseAdapter(items, onClicker, R.layout.rv_questions) {

    override fun View.bind(item: Any, position: Int) {
        val data = item as ResultModel
        this.tvNumber.text = "${position.plus(1)} )"

        if (position.plus(1) == items.size) {
            view.gone()
        } else {
            view.visible()
        }
        this.tvQuestions.text = data.question
        for (i in 0 until data.getAllQuestions().size) {
            val button = RadioButton(context)
            button.id = View.generateViewId()
            button.text = data.getAllQuestions()[i]
            button.isChecked = data.isChecked(i)
            this.rgQuestionAnswerList.addView(button)
        }
        this.rgQuestionAnswerList.setOnCheckedChangeListener { radioGroup, i ->
            val checkedButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            items[position].selectedAnswer = checkedButton.text.toString()
        }
    }
}