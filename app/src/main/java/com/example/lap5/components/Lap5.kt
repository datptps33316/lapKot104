package com.example.lap5.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lap5.viewmodel.QuestionViewModel

@Composable
fun Lap5() {

    val viewModel: QuestionViewModel = viewModel();
    val step by viewModel.step.collectAsState();
    val firstNumber by viewModel.firstNumber.collectAsState();
    val secondNumber by viewModel.secondNumber.collectAsState();
    val calculation by viewModel.calculation.collectAsState();
    val result by viewModel.result.collectAsState();
    val question by viewModel.question.collectAsState();
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }


    fun checkResult() {
        if (question.firstNumber.toString() != firstNumber.toString()) {
            Toast.makeText(context, "sai ", Toast.LENGTH_SHORT).show()
            return;
        }
        if (question.secondNumber.toString() != secondNumber.toString()) {
            Toast.makeText(context, "sai  ", Toast.LENGTH_SHORT).show()
            return;
        }
        if (question.calculation.toString() != calculation.toString()) {
            Toast.makeText(context, "sai ", Toast.LENGTH_SHORT).show()
            return;
        }
        if (question.result.toString() != result.toString()) {
            Toast.makeText(context, "sai ", Toast.LENGTH_SHORT).show()
            return;
        } else {
            Toast.makeText(context, "Đúng  ", Toast.LENGTH_SHORT).show();
            viewModel.nextStep(step);
        }
    }


    Row(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = firstNumber.toString(),
            modifier = Modifier
                .width(51.dp)
                .height(51.dp)
                .onKeyEvent {
                    if (it.key == Key.Enter) {
                        checkResult();
                        true
                    } else {
                        false
                    }
                },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB5E4A4),
                unfocusedBorderColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            onValueChange = { viewModel.updateFirstNumber(it.trim()) })
        OutlinedTextField(
            value = calculation.toString(),
            modifier = Modifier
                .width(51.dp)
                .height(51.dp)
                .onKeyEvent {
                    if (it.key == Key.Enter) {
                        checkResult();
                        true
                    } else {
                        false
                    }
                } ,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB5E4A4),
                unfocusedBorderColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            onValueChange = { viewModel.updateSecondNumber(it.trim()) })
        OutlinedTextField(
            value = secondNumber.toString(),
            modifier = Modifier
                .width(51.dp)
                .height(51.dp)
                .onKeyEvent {
                    if (it.key == Key.Enter) {
                        checkResult();
                        true
                    } else {
                        false
                    }
                }
               ,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB5E4A4),
                unfocusedBorderColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            onValueChange = {
                viewModel.updateCalculation(
                    it.trim()
                )
            })
        Text(
            text = "=",
            fontSize = 20.sp,
            color = Color.Red
        )
        OutlinedTextField(
            value = result.toString(),
            modifier = Modifier
                .width(51.dp)
                .height(51.dp)
                .onKeyEvent {
                    if (it.key == Key.Enter) {
                        checkResult();
                        true
                    } else {
                        false
                    }
                }
                ,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB5E4A4),
                unfocusedBorderColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            onValueChange = { viewModel.updateResult(it.trim()) },

            )
    }



}

