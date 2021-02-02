package com.rapidzz.tirivia_application_assignment.view.fragments

import androidx.lifecycle.observe
import com.rapidzz.tirivia_application_assignment.R
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.ResultModel
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.SolvedQuestionModel
import com.rapidzz.tirivia_application_assignment.utils.application.*
import com.rapidzz.tirivia_application_assignment.view.adapters.BaseAdapter
import com.rapidzz.tirivia_application_assignment.view.adapters.QuestionsAdapter
import com.rapidzz.tirivia_application_assignment.view.dialogs.ConfirmationDialog
import com.rapidzz.tirivia_application_assignment.viewModels.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_questions.*
import java.util.ArrayList

class QuestionListFragment : BaseFragment(R.layout.fragment_questions), BaseAdapter.OnItemClicker {

    val questionAdapter: QuestionsAdapter by lazy {
        QuestionsAdapter(questions, this)
    }

    val optionType by lazy {
        arguments?.let {
            QuestionListFragmentArgs.fromBundle(it).optionType
        }
    }

    val difficultyLevel by lazy {
        arguments?.let {
            QuestionListFragmentArgs.fromBundle(it).difficultyLevel
        }
    }

    val category by lazy {
        arguments?.let {
            QuestionListFragmentArgs.fromBundle(it).category
        }
    }

    val pointFactor by lazy {
        when (difficultyLevel) {
            LEVEL_EASY -> {
                1
            }
            LEVEL_MEDIUM -> {
                2
            }
            LEVEL_HARD -> {
                3
            }
            else -> {
                1
            }
        }
    }

    override fun initViews() {
        setToolbarTitle(getString(R.string.question_list), true, false)
        rvQuestions.adapter = QuestionsAdapter(questions, this)

        btnEndQuiz.setOnClickListener { validateAndSubmitQuiz() }
    }

    private val viewModel: QuestionViewModel by lazy { obtainViewModel(QuestionViewModel::class.java) }
    private val questions = ArrayList<ResultModel>()
    override fun attachViewModel() {
        with(viewModel) {
            setGeneralViewModel(this)
            questionsListingsLiveData.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    questions.clear()
                    questions.addAll(it.results)
                    rvQuestions.adapter?.notifyDataSetChanged()
                }
            }
            getAllQuestions(
                10,
                category?.category_id.toString(),
                difficultyLevel,
                optionType
            )
        }
    }

    private fun validateAndSubmitQuiz() {
        val allQuestions = questionAdapter.items
        var isAllSubmitted = true
        for (i in 0 until allQuestions.size) {
            if (allQuestions[i].selectedAnswer.isNullOrEmpty()) {
                showAlertDialog("Please answer question # " + (i + 1))
                isAllSubmitted = false
                break
            }
        }
        if (isAllSubmitted) {
            showResults()
        }

    }


    private fun submitQuiz(solvedQuestionModel: List<SolvedQuestionModel>) {
        viewModel.updateCategory(category!!)
        viewModel.insertAllQuestions(solvedQuestionModel)
    }


    private fun showResults() {
        val allQuestions = questionAdapter.items
        val correctAnswers =
            allQuestions.filter { it.selectedAnswer.equals(it.correct_answer) }.count()
        val pointsEarned = correctAnswers.times(pointFactor)
        val report =
            " Total questions : " + allQuestions.size + " \n Correct answers : " + correctAnswers +
                    " \n Incorrect answers : " + (allQuestions.size - correctAnswers) + "\n\n Points Earned : " + pointsEarned.toString()

        val allSolvedQuestions = allQuestions.map {
            SolvedQuestionModel(
                0,
                category?.category_id.toString(),
                it.correct_answer,
                it.difficulty,
                it.question,
                it.type,
                it.selectedAnswer.toString()
            )
        }
        showResultsDialog(report, pointsEarned, allSolvedQuestions)

    }

    private fun showResultsDialog(
        report: String,
        pointsEarned: Int,
        allSolvedQuestionModels: List<SolvedQuestionModel>
    ) {
        ConfirmationDialog(true, report, getString(R.string.Result),
            object : ConfirmationDialog.ConfirmationListener {
                override fun isConfirmed(isConfirmed: Boolean) {
                    if (isConfirmed) {
                        category?.earned_points = category!!.earned_points.plus(pointsEarned)
                        submitQuiz(allSolvedQuestionModels)
                        onBackPress()
                    }
                }
            }).show(childFragmentManager, "")
    }

    override fun onItemClick(position: Int, data: Any) {

    }
}