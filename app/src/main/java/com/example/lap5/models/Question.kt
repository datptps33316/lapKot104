package com.example.lap5.models

class Question(var firstNumber :Int ,var  secondNumber :Int ,var  calculation :String ,var  result :Int){

    operator fun component1() = firstNumber
    operator fun component2() = secondNumber

}