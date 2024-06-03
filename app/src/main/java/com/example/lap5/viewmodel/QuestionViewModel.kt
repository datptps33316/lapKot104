package com.example.lap5.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lap5.models.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class QuestionViewModel : ViewModel() {

    private val _step = MutableStateFlow(0);
    val step: StateFlow<Int> = _step


    private val _questions = MutableStateFlow(
        listOf(
            Question(1, 1, "+", 2),
            Question(2, 2 , "+", 4),
            Question(2, 3  , "x", 6),
        )
    )
    private val questions: StateFlow<List<Question>> = _questions
    private val _question = MutableStateFlow(questions.value[step.value]);
    val question: MutableStateFlow<Question> = _question
    fun initNumber(arr: Array<out Any>): Any {
        val random = (0..3).random();
        arr[random] = "" as Nothing;
        return arr
    }


    private val questionArr = arrayOf(
        _question.value.firstNumber,
        _question.value.secondNumber,
        _question.value.calculation, _question.value.result
    )

    val v = initNumber(questionArr)

    private val _firstNumber = MutableStateFlow(questionArr[0])
    val firstNumber: MutableStateFlow<Any> = _firstNumber

    private val _secondNumber = MutableStateFlow(questionArr[1])
    val secondNumber: MutableStateFlow<Any> = _secondNumber

    private val _calculation = MutableStateFlow(questionArr[2])
    val calculation: MutableStateFlow<Any> = _calculation

    private val _result = MutableStateFlow(questionArr[3])
    val result: MutableStateFlow<Any> = _result

    fun updateFirstNumber(value: String) {
        _firstNumber.value = value
    }

    fun updateSecondNumber(value: String) {
        _secondNumber.value = value
    }

    fun updateCalculation(value: String) {
        _calculation.value = value
    }

    fun updateResult(value: String) {
        _result.value = value
    }

    fun nextStep(step: Int) {
        if (_step.value < _questions.value.size - 1) {
            _step.update { it + 1 }
            updateQuestionValues()
        }
    }


    private fun updateQuestionValues() {
        val question = _questions.value[_step.value]
        val questionArr = arrayOf(
            question.firstNumber,
            question.secondNumber,
            question.calculation,
            question.result
        )
        val v = initNumber(questionArr)
        _question.value=question
        _firstNumber.value = questionArr[0]
        _secondNumber.value = questionArr[1]
        _calculation.value = questionArr[2]
        _result.value = questionArr[3]
    }
}


