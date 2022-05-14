package ir.digimoplus.bottom_navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.LocalTextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class TabValue(
    val title: String,
    @DrawableRes val iconId: Int,
    val tabBackgroundColor: Color = Color.Cyan,
    val tabHeight: Dp = 50.dp,
    val titleColor: Color = Color.Black,
    val tabCorner: Float = 35f,
    val titleStyle: TextStyle? = null,
    val badgeValue: Int = 0,
    val badgeBackgroundColor: Color = Color.Red,
    )
