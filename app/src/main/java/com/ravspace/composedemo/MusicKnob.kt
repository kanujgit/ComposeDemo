package com.ravspace.composedemo

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.atan2

class MusicKnob {


    @Composable
    fun CreateMusicKnob() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                    .padding(30.dp)
            )
            {

                var volume by remember {
                    mutableFloatStateOf(0f)
                }

                val barCount = 20

                MusicKnob(
                    modifier = Modifier.size(100.dp)
                ) {
                    volume = it
                }

                Spacer(modifier = Modifier.width(20.dp))


                VolumeBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    activeBars = (barCount * volume).toInt(),
                    barCount = barCount
                )
            }


        }
    }


    @Composable
    fun VolumeBar(modifier: Modifier, activeBars: Int = 0, barCount: Int = 0) {
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            val barWidth = remember {
                constraints.maxWidth / (2f * barCount)
            }

            Canvas(modifier = modifier) {
                for (i in 0 until barCount) {
                    drawRoundRect(
                        color = if (i in 0..activeBars) Color.Green else Color.Red,
                        topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                        size = Size(barWidth, constraints.maxHeight.toFloat()),
                        cornerRadius = CornerRadius(0f)

                    )
                }
            }
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("NotConstructor")
    @Composable
    fun MusicKnob(modifier: Modifier, limitingAngle: Float = 25f, onValueChange: (Float) -> Unit) {
        var rotation by remember {
            mutableStateOf(limitingAngle)
        }
        var touchX by remember {
            mutableStateOf(0f)
        }
        var touchY by remember {
            mutableStateOf(0f)
        }
        var centerX by remember {
            mutableStateOf(0f)
        }
        var centerY by remember {
            mutableStateOf(0f)
        }

        Image(painter = painterResource(id = R.drawable.music_knob),
            contentDescription = "Music Knob",
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    val windowsBound = it.boundsInWindow()
                    centerX = windowsBound.size.width / 2f
                    centerY = windowsBound.size.height / 2f
                }
                .pointerInteropFilter { event ->
                    touchX = event.x
                    touchY = event.y
                    val angle =
                        -atan2(centerX - touchX, centerY - touchY) * (180 / Math.PI).toFloat()

                    when (event.action) {
                        MotionEvent.ACTION_DOWN,
                        MotionEvent.ACTION_MOVE -> {
                            if (angle !in -limitingAngle..limitingAngle) {
                                val fixedAngle = if (angle in -180f..-limitingAngle) {
                                    360f + angle
                                } else {
                                    angle
                                }
                                rotation = fixedAngle

                                val percentage =
                                    (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                                onValueChange(percentage)
                                true

                            } else {
                                false
                            }
                        }

                        else -> false
                    }
                }.rotate(rotation))
    }

}