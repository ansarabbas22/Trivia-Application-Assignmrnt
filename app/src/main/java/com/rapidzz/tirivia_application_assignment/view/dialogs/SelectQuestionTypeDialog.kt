package com.rapidzz.tirivia_application_assignment.view.dialogs

import android.view.View
import com.rapidzz.tirivia_application_assignment.R
import com.rapidzz.tirivia_application_assignment.utils.application.*
import com.rapidzz.tirivia_application_assignment.utils.generalUtils.AppConstants
import com.rapidzz.tirivia_application_assignment.view.fragments.HomeFragment
import kotlinx.android.synthetic.main.fragment_question_type.view.*

class SelectQuestionTypeDialog : BaseDialog() {


    var difficultyLevel: String? = null
    var optionType: String? = null

    override fun initViews(view: View) {
        view.btnNext.setOnClickListener {
            if (optionType.isNullOrEmpty()) {
                showToast("Please select option type")
            } else if (difficultyLevel.isNullOrEmpty()) {
                showToast("Please select difficulty")
            } else {
                (parentFragment as HomeFragment).openQuestionWithSelections(
                    difficultyLevel.toString(),
                    optionType.toString()
                )
                dismiss()
            }
        }

        view.llCloseDialog.setOnClickListener {
            this.dismiss()
        }

        view.spDifficulty.observe {
            when (it) {
                0 -> {
                    difficultyLevel = null
                }

                1 -> {
                    difficultyLevel = LEVEL_EASY
                }
                2 -> {
                    difficultyLevel = LEVEL_MEDIUM
                }
                3 -> {
                    difficultyLevel = LEVEL_HARD
                }
            }
        }

        view.spOptionType.observe {
            when (it) {
                0 -> {
                    optionType = null
                }

                1 -> {
                    optionType = TYPE_MCQ
                }
                2 -> {
                    optionType = TYPE_TF
                }

            }
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_question_type
    }

    companion object {
        fun newInstance(): SelectQuestionTypeDialog {
            var fragment = SelectQuestionTypeDialog()
            return fragment
        }
    }
}