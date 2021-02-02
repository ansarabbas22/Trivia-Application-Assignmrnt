package com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels

data class ResultModel(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: ArrayList<String>,
    val question: String,
    val type: String,
    var selectedAnswer: String? = null
) {
    fun getAllQuestions(): ArrayList<String> {
        val questions: ArrayList<String> = ArrayList()
        questions.add(correct_answer)
        questions.addAll(incorrect_answers)
        return questions
    }


    fun isChecked(index: Int): Boolean {
        return if (selectedAnswer.isNullOrEmpty()) {
            false
        } else {
            (getAllQuestions()[index].equals(selectedAnswer, true))
        }
    }
}