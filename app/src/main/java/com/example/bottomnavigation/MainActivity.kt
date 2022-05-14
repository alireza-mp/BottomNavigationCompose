package com.example.bottomnavigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottomnavigation.ui.theme.BottomNavigationTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ir.digimoplus.bottom_navigation.CustomBottomNavigation
import ir.digimoplus.bottom_navigation.TabValue


@ExperimentalMaterialApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                val pagerState = rememberPagerState(pageCount = 3)

                HorizontalPager(modifier = Modifier.fillMaxSize(), state = pagerState) { index ->
                    when (index) {
                        0 -> Display(text = "tab 1")
                        1 -> Display(text = "tab 2")
                        2 -> Display(text = "tab 3")
                    }
                }

                Card(
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(bottom = 8.dp, end = 12.dp, start = 12.dp)
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 4.dp,
                ) {
                    CustomBottomNavigation(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .height(80.dp),
                        pagerState = pagerState,
                        tabValues = listOf(
                            TabValue(
                                "Home",
                                R.drawable.home,
                                badgeValue = 3
                            ), TabValue(
                                "Profile",
                                R.drawable.home
                            ), TabValue(
                                "Basket",
                                R.drawable.home,
                                badgeValue = 7
                            ), TabValue(
                                "Chat",
                                R.drawable.home
                            )
                        )
                    )
                }

            }

        }
    }
}

@Composable
fun Display(text: String) {
    Text(text = text, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}

