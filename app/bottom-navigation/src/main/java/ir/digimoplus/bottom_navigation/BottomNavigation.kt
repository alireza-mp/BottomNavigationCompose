package ir.digimoplus.bottom_navigation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp),
    pagerState: PagerState? = null,
    tabValues: List<TabValue>,
    onTabClickListener: (item: Int) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    var size = 0.dp

    // home tab is default select tab
    // we have four tab but one tab is not changed (basket tab)
    val navigationBottomState = remember {
        mutableStateOf(BottomNavigationModel(tab1 = true, tab2 = false, tab3 = false))
    }

    // select tab if pager has changed
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState?.currentPage }.collect { page ->
            page?.let {
                when (it) {
                    0 -> navigationBottomState.value =
                        BottomNavigationModel(tab1 = true, tab2 = false, tab3 = false)

                    1 -> navigationBottomState.value =
                        BottomNavigationModel(tab1 = false, tab2 = true, tab3 = false)

                    2 -> navigationBottomState.value =
                        BottomNavigationModel(tab1 = false, tab2 = false, tab3 = true)
                }
            }
        }
    }

    BoxWithConstraints(modifier = modifier
    ) {

        // tab size  = 34% width from card width size
        size = ((maxWidth / 100) * 34)

        Row(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            // tab one
            NavTab(
                state = navigationBottomState.value.tab1,
                title = tabValues[0].title,
                size = size,
                id = tabValues[0].iconId,
                tabBackgroundColor = tabValues[0].tabBackgroundColor,
                height = tabValues[0].tabHeight,
                titleColor = tabValues[0].titleColor,
                corner = tabValues[0].tabCorner,
                titleStyle = tabValues[0].titleStyle ?: LocalTextStyle.current,
                badgeValue = tabValues[0].badgeValue,
                badgeBackgroundColor = tabValues[0].badgeBackgroundColor
            ) {
                navigationBottomState.value =
                    BottomNavigationModel(tab1 = true, tab2 = false, tab3 = false)
                coroutineScope.launch {
                    pagerState?.animateScrollToPage(0)
                }
                onTabClickListener(0)
            }

            //tab two
            NavTab(
                state = navigationBottomState.value.tab2,
                title = tabValues[1].title,
                size = size,
                id = tabValues[1].iconId,
                tabBackgroundColor = tabValues[1].tabBackgroundColor,
                height = tabValues[1].tabHeight,
                titleColor = tabValues[1].titleColor,
                corner = tabValues[1].tabCorner,
                titleStyle = tabValues[1].titleStyle ?: LocalTextStyle.current,
                badgeValue = tabValues[1].badgeValue,
                badgeBackgroundColor = tabValues[1].badgeBackgroundColor
            ) {
                navigationBottomState.value =
                    BottomNavigationModel(tab1 = false, tab2 = true, tab3 = false)
                coroutineScope.launch {
                    pagerState?.animateScrollToPage(1)
                }
                onTabClickListener(1)
            }

            // this tab not changed (basket tab)
            NavTab(
                icon = tabValues[2].iconId,
                height = tabValues[2].tabHeight,
                size = size,
                badgeValue = tabValues[2].badgeValue,
                badgeBackgroundColor = tabValues[2].badgeBackgroundColor
            ) {
                onTabClickListener(2)
            }

            //tab three
            NavTab(
                state = navigationBottomState.value.tab3,
                title = tabValues[3].title,
                size = size,
                id = tabValues[3].iconId,
                tabBackgroundColor = tabValues[3].tabBackgroundColor,
                height = tabValues[3].tabHeight,
                titleColor = tabValues[3].titleColor,
                corner = tabValues[3].tabCorner,
                titleStyle = tabValues[3].titleStyle ?: LocalTextStyle.current,
                badgeValue = tabValues[3].badgeValue,
                badgeBackgroundColor = tabValues[3].badgeBackgroundColor
            ) {
                navigationBottomState.value =
                    BottomNavigationModel(tab1 = false, tab2 = false, tab3 = true)
                coroutineScope.launch {
                    pagerState?.animateScrollToPage(2)
                }
                onTabClickListener(3)
            }
        }

    }
}