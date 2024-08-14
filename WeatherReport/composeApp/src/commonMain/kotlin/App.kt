import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import data.api.apiModule
import data.repository.repositoryModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import ui.current.CurrentScreen
import ui.current.CurrentViewModel
import ui.japan.JapanScreen
import ui.japan.JapanViewModel

@Composable
@Preview
fun App() {
    startKoin {
        modules(appModule, apiModule, repositoryModule)
    }

    MaterialTheme {
        TabNavigator(HomeTab) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = it.current.options.title)
                        }
                    )
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(SecondTab)
                    }
                }
            ) {
                CurrentTab()
            }
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

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Text(text = tab.options.title) }
    )
}

object HomeTab : Tab, KoinComponent {
    private val viewModel: JapanViewModel by inject()

    @Composable
    override fun Content() {
        JapanScreen(viewModel = viewModel)
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            val scope = rememberCoroutineScope()
//            var text by remember { mutableStateOf("Loading") }
//            LaunchedEffect(true) {
//                scope.launch {
//                    text = try {
//                        Greeting().greet()
//                    } catch (e: Exception) {
//                        e.message ?: "error"
//                    }
//                }
//            }
//            Text(text = GreetingHelper().greet())
//            GreetingView(text)
//        }
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "全国",
                icon = null
            )
        }
}

object SecondTab : Tab, KoinComponent {
    private val viewModel: CurrentViewModel by inject()

    @Composable
    override fun Content() {
        CurrentScreen(viewModel = viewModel)
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 1u,
                title = "現在地",
                icon = null
            )
        }
}
