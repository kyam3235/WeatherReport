import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import weatherreport.composeapp.generated.resources.Res
import weatherreport.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface (
            modifier = Modifier.fillMaxSize()
        ){
            val scope = rememberCoroutineScope()
            var text by remember { mutableStateOf("Loading") }
            LaunchedEffect(true){
                scope.launch {
                    text = try {
                        Greeting().greet()
                    }catch (e: Exception) {
                        e.message ?: "error"
                    }
                }
            }
            GreetingView(text)
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        GreetingView("Hello Android!")
    }
}
