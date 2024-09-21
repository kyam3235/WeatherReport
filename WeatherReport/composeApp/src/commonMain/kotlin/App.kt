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
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import ui.current.CurrentScreen
import ui.current.CurrentViewModel
import ui.japan.JapanScreen
import ui.japan.JapanViewModel
import weatherreport.composeapp.generated.resources.Res
import weatherreport.composeapp.generated.resources.app_tab_title_0
import weatherreport.composeapp.generated.resources.app_tab_title_1

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    val tabTitles = listOf(
        stringResource(Res.string.app_tab_title_0),
        stringResource(Res.string.app_tab_title_1)
    )

    startKoin {
        modules(appModule, apiModule, repositoryModule)
    }

    MaterialTheme {
        TabNavigator(HomeTab) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = tabTitles[it.current.options.index.toInt()])
                        }
                    )
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab, tabTitles[0])
                        TabNavigationItem(SecondTab, tabTitles[1])
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab, title: String) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Text(text = title) }
    )
}

object HomeTab : Tab, KoinComponent {
    private val viewModel: JapanViewModel by inject()

    @Composable
    override fun Content() {
        JapanScreen(viewModel = viewModel)
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "",
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
                title = "",
                icon = null
            )
        }
}
