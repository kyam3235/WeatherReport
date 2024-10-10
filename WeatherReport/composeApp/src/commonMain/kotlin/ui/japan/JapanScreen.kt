package ui.japan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun JapanScreen(viewModel: JapanViewModel) {
    val state = viewModel.container.stateFlow.collectAsState().value

//    ConstraintLayout(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val (
//            date,
//            japan,
//            sapporo,
//            tokyo,
//            nagoya,
//            osaka,
//            fukuoka
//        ) = createRefs()
//
//        // date
//        Text(
//            modifier = Modifier.constrainAs(date) {
//                top.linkTo(parent.top, margin = 16.dp)
//                start.linkTo(parent.start, margin = 16.dp)
//            },
//            text = state.date,
//            style = MaterialTheme.typography.h4
//        )
//
//        // japan
//        Image(
//            modifier = Modifier.constrainAs(japan) {
//                top.linkTo(parent.top)
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//                bottom.linkTo(parent.bottom)
//            },
//            painter = painterResource(Res.drawable.japan),
//            contentDescription = null
//        )
//
//        // sapporo
//        state.weathers.find { it.city.name == City.SAPPORO.name }?.let { weather ->
//            TwoDaysCard(
//                modifier = Modifier.constrainAs(sapporo) {
//                    end.linkTo(japan.end)
//                    bottom.linkTo(japan.top)
//                },
//                twoDaysWeather = weather
//            )
//        }
//
//        // tokyo
//        state.weathers.find { it.city.name == City.TOKYO.name }?.let { weather ->
//            TwoDaysCard(
//                modifier = Modifier.constrainAs(tokyo) {
//                    top.linkTo(japan.top)
//                    end.linkTo(japan.end)
//                    bottom.linkTo(japan.bottom)
//                },
//                twoDaysWeather = weather
//            )
//        }
//
//        // nagoya
//        state.weathers.find { it.city.name == City.NAGOYA.name }?.let { weather ->
//            TwoDaysCard(
//                modifier = Modifier.constrainAs(nagoya) {
//                    top.linkTo(japan.bottom)
//                    end.linkTo(japan.end)
//                },
//                twoDaysWeather = weather
//            )
//        }
//
//        // osaka
//        state.weathers.find { it.city.name == City.OSAKA.name }?.let { weather ->
//            TwoDaysCard(
//                modifier = Modifier.constrainAs(osaka) {
//                    top.linkTo(japan.top)
//                    start.linkTo(japan.start)
//                    bottom.linkTo(japan.bottom)
//                },
//                twoDaysWeather = weather
//            )
//        }
//
//        // fukuoka
//        state.weathers.find { it.city.name == City.FUKUOKA.name }?.let { weather ->
//            TwoDaysCard(
//                modifier = Modifier.constrainAs(fukuoka) {
//                    top.linkTo(japan.bottom)
//                    start.linkTo(japan.start)
//                },
//                twoDaysWeather = weather
//            )
//        }
//    }
}