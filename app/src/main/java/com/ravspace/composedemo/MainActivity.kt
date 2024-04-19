package com.ravspace.composedemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ravspace.composedemo.MainActivity.CustomLayoutsSnippet2.firstBaselineToTop
import com.ravspace.composedemo.ui.tweetsty.TweetsAppDemo
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    companion object {
        const val TAG = "ComposeDemo"
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.anuj)
            val description = "This is an image"
            val title = "Anuj photo gallery"


//            ColorBox(
//                modifier = Modifier
//                    .fillMaxSize()
//            )


//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                contentAlignment = Alignment.Center,
//            ) {
//                ImageCard(
//                    painter = painter,
//                    contentDescription = description, title = title, modifier = Modifier
//                )
//            }

            //list use cases
            //      ListDemo()

            //constrain layout
//            ConstrainLayoutDemo()

            //effect handler
            //EffectDemo()

            //animation demo
            //AnimationDemo()

            //progress bar
            //ProgressBarAnimation()

            //MusicKnob()
            //MusicKnob().CreateMusicKnob()

            //Mediation UI
            //  MediationUI().HomeScreen()

            //timer ui

            //   Timer().SetTimer()


            //Quote App
//            CoroutineScope(Dispatchers.IO).launch {
//                DataManager.loadAssetFromFiles(this@MainActivity)
//            }

            //  LaunchQuoteApp()

            //launchEffect
            //LaunchEffectDemo()
            // disposableEffect
            //DisposableEffectDemo()

            //Derived state
            //DerivedAndProduceStateDemo()

            //TweetsAppDemo
            TweetsAppDemo()

            //weight demo
            //  WeightDemo()
            //custum layout
            TextWithPaddingToBaselinePreview()
            TextWithNormalPaddingPreview()
        }
    }

    private object CustomLayoutsSnippet2 {
        // [START android_compose_layouts_first_baseline]
        fun Modifier.firstBaselineToTop(
            firstBaselineToTop: Dp
        ) = layout { measurable, constraints ->
            // Measure the composable
            val placeable = measurable.measure(constraints)

            // Check the composable has a first baseline
            check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
            val firstBaseline = placeable[FirstBaseline]

            // Height of the composable with padding - first baseline
            val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
            val height = placeable.height + placeableY
            layout(placeable.width, height) {
                // Where the composable gets placed
                placeable.placeRelative(0, placeableY)
            }
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    private fun WeightDemo() {
        Row(modifier = Modifier.width(100.dp)) {
            Image(
                modifier = Modifier.weight(4f),
                painter = painterResource(id = R.drawable.anuj),
                contentDescription = "Anuj"
            )
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(text = "Anuj Image")
            }

        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun TextWithPaddingToBaselinePreview() {

        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun TextWithNormalPaddingPreview() {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }

    /*
    * Derived state is used to calculate the value based on the other state
    * Produce state is used to calculate the value based on the other state and it is used to perform the async operation
    *
     */
    @SuppressLint("UnrememberedMutableState")
    @Composable
    private fun DerivedAndProduceStateDemo() {
        val tableOf = remember {
            mutableStateOf(2)
        }
//        val index = remember {
//            mutableStateOf(1)
//        }

        val index = produceState(initialValue = 1) {
            repeat(10) {
                delay(1000L)
                value += 1
            }
        }

        val message =
            derivedStateOf { "Table of ${tableOf.value} * $index = ${tableOf.value * index.value}" }

        Column {
            Text(text = message.value)
            Button(onClick = {
//                tableOf.value = 3
//                index.value = 2
            }) {
                Text("Change Table")
            }
        }
    }

    /*
    * DisposableEffect is used to add and remove listeners
    * If keyboard is visible then it will print the log
     */
    @Composable
    private fun DisposableEffectDemo() {
        val view = LocalView.current
        DisposableEffect(key1 = Unit) {
            val listener = {
                ViewTreeObserver.OnGlobalLayoutListener { ->
                    val inset = ViewCompat.getRootWindowInsets(view)
                    val isKeyboardVisisble = inset?.isVisible(WindowInsetsCompat.Type.ime())
                    Log.d(TAG, "IME is visible $isKeyboardVisisble}")
                }

            }
            view.viewTreeObserver.addOnGlobalLayoutListener { listener }
            onDispose {
                view.viewTreeObserver.removeOnDrawListener { listener }
            }
        }
    }

    @Composable
    private fun LaunchEffectDemo() {
        //bases on the key, if key is changed then only effect will be launched
        LaunchedEffect(key1 = "Anuj") {
            delay(1000L)
            println("Launched Effect")
        }
    }

    @Composable
    private fun ProgressBarAnimation() {

    }

    @Composable
    private fun AnimationDemo() {
        var sizeState by remember {
            mutableStateOf(200.dp)
        }
        val size by animateDpAsState(
            targetValue = sizeState, label = "Nothing", animationSpec = tween(durationMillis = 5000)
            //, animationSpec =  spring( Spring.DampingRatioHighBouncy)
//                , animationSpec = keyframes{
//                    durationMillis = 5000
//                    sizeState at 0 with LinearEasing
//                    sizeState*1.5f at 100  with FastOutLinearInEasing
//                    sizeState*2f at 500
//            }
        )
        val infiniteTransition = rememberInfiniteTransition(label = "ok")
        val color by infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = InfiniteRepeatableSpec(
                tween(durationMillis = 5000), repeatMode = RepeatMode.Restart
            ),
            label = "Nothing",
        )


        Box(
            modifier = Modifier
                .size(size = size)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Button(onClick = {
                    sizeState += 50.dp
                }) {
                    Text("Increase Size")
                }
                Button(onClick = {
                    sizeState -= 50.dp
                }) {
                    Text("Decrease Size")
                }
            }

        }
    }

    @Composable
    private fun EffectDemo() {
        val text by remember {
            mutableStateOf("")
        }
        LaunchedEffect(key1 = text) {
            delay(1000L)
            println("Text is $text")
        }

    }

    @Composable
    private fun ConstrainLayoutDemo() {
        val constrains: ConstraintSet = ConstraintSet {
            val greenBox = createRefFor("greenBox")
            val redBox = createRefFor("redBox")
            val guideline = createGuidelineFromTop(0.5f)

            constrain(greenBox) {
                top.linkTo(guideline)
                start.linkTo(parent.start)
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            constrain(redBox) {
                top.linkTo(parent.top)
                start.linkTo(greenBox.end)
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }
            createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)

        }


        ConstraintLayout(constraintSet = constrains, modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenBox")
            ) {}

            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redBox")
            ) {}

        }
    }

    @Composable
    fun ListDemo() {
        val scrollState = rememberScrollState()
        LazyColumn {
            itemsIndexed(
                listOf(
                    "Anuj",
                    "Rav",
                    "Ravi",
                    "Ravindra",
                    "Ravindra Singh",
                    "Ravindra Singh Rajput",
                    "Ravindra Singh Rajput Anuj",
                    "Ravindra Singh Rajput Anuj Singh",
                    "Ravindra Singh Rajput Anuj Singh Rajput",
                    "Ravindra Singh Rajput Anuj Singh Rajput Anuj"
                )
            ) { index, name ->
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }

//            repeat(50) {
//                Text(
//                    text = "Item $it", modifier = Modifier
//                        .padding(16.dp)
//                        .fillMaxWidth(), fontSize = 24.sp, textAlign = TextAlign.Center
//                )
//            }
        }
    }

    @Composable
    fun ColorBox(modifier: Modifier = Modifier) {
        val color = remember {
            mutableStateOf(Color.Red)
        }

        Box(modifier = modifier
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f
                )
            })
    }


    @Composable
    fun ImageCard(
        painter: Painter, contentDescription: String, title: String, modifier: Modifier
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

        ) {

            Box(modifier = Modifier.height(400.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black), startY = 300f
                            )

                        )
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                Color.Green, fontSize = 30.sp
                            )
                        ) {
                            append("Anuj")
                        }
                        append("Photo Gallery")
                    })
                }
            }
        }
    }

}
