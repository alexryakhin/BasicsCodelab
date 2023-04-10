package com.dor.basicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dor.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MyApp()
            }
        }
    }
}

val numbers = List(size = 1000) { "$it" }

@Composable
fun MyApp() {
    var shouldShowWelcome by rememberSaveable { mutableStateOf(true) }
    
    if (shouldShowWelcome) {
        WelcomeScreen(onContinueClick = { shouldShowWelcome = false })
    } else {
        Greetings()
    }
}

@Composable
fun WelcomeScreen(
    onContinueClick: () -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Welcome to\nBasics of Jetpack Compose",
                modifier = Modifier
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = { onContinueClick() }) {
                Text("Continue")
            }
        }
    }
}

@Composable
fun Greetings() {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//    ) {
//        for (name in listOf<String>("World", "Android")) {
//            MyHelloTo(name = name)
//        }
//    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        item { Text(text = "Header") }
        items(numbers) { number ->
            MyHelloTo(name = number)
        }
    }
}

@Composable
fun MyHelloTo(name: String) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(
        targetValue = if (isExpanded) 40.dp else 0.dp
    )
    Surface(color = MaterialTheme.colors.primary, shape = RoundedCornerShape(size = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(bottom = extraPadding)
        ) {
            Text(text = "Hello\n$name", modifier = Modifier.weight(1f))
            OutlinedButton(onClick = { isExpanded = !isExpanded }) {
                Text(if (isExpanded) "Show less" else "Show more")
            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
//@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_NO)
//@Composable
//fun WelcomePreview() {
//    BasicsCodelabTheme {
//        WelcomeScreen(onContinueClick = {})
//    }
//}