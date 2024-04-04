package com.ravspace.composedemo.ui.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Timer {


    @Composable
    fun SetTimer(){
        Surface(
            color = Color(0xFF101010),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Timer().TimerHome(
                    totalTimer = 100L * 1000L,
                    handleColor = Color.Green,
                    inactiveBarColor = Color.DarkGray,
                    activeBarColor = Color(0xFF37B900),
                    modifier = Modifier.size(200.dp)
                )
            }
        }

    }

    @Composable
    fun TimerHome(
        totalTimer: Long,
        handleColor: Color,
        inactiveBarColor: Color,
        activeBarColor: Color,
        modifier: Modifier,
        initialValue: Float = 1f,
        strokeWidth: Dp = 5.dp
    ) {

        var size by remember {
            mutableStateOf(IntSize.Zero)
        }

        var value by remember {
            mutableStateOf(initialValue)
        }

        var currentTime by remember {
            mutableStateOf(totalTimer)
        }

        var isTimerRunning by remember {
            mutableStateOf(false)
        }


        LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
            if (currentTime > 0 && isTimerRunning) {
                delay(100L)
                currentTime -= 100L
                value = currentTime / totalTimer.toFloat()
            }
        }

        Box(contentAlignment = Alignment.Center, modifier = Modifier.onSizeChanged {
            size = it
        }) {

            Canvas(modifier = modifier) {
                drawArc(
                    color = inactiveBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )

                drawArc(
                    color = activeBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f * value,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )

                val center = Offset(size.width / 2f, size.height / 2f)
                val beta = (250 * value + 145f) * (PI / 180f).toFloat()
                val radious = size.width / 2f

                val a = cos(beta) * radious
                val b = sin(beta) * radious

                drawPoints(
                    listOf(Offset(center.x + a, center.y + b)),
                    pointMode = PointMode.Points,
                    color = handleColor,
                    strokeWidth = (strokeWidth.toPx() * 3f),
                    cap = StrokeCap.Round
                )

            }

            Text(
                text = (currentTime / 1000L).toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Button(
                onClick = {
                    if (currentTime <= 0L) {
                        currentTime = totalTimer
                        isTimerRunning = true
                    } else {
                        isTimerRunning = !isTimerRunning
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isTimerRunning || currentTime <= 0L) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                )


            ) {

                Text(
                    text = if (!isTimerRunning || currentTime <= 0L) {
                        "Stop"
                    } else if (!isTimerRunning || currentTime >= 0L) {
                        "Start"
                    } else {
                        "Restart"
                    }
                )
            }
        }
    }
}