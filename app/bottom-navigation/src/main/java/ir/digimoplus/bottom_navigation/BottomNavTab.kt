package ir.digimoplus.bottom_navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun NavTab(
    state: Boolean,
    @DrawableRes id: Int,
    title: String,
    size: Dp,
    tabBackgroundColor: Color = Color.Cyan,
    height: Dp = 50.dp,
    titleColor: Color = Color.Black,
    corner: Float,
    titleStyle: TextStyle = LocalTextStyle.current,
    badgeValue: Int = 0,
    badgeBackgroundColor: Color,
    onClick: () -> Unit,
) {

    val model = remember {
        mutableStateOf(TabModel(
            width = (size - 50.dp),
            corner = 25f,
            alpha = 0f,
            titleWidth = 0.dp,
            iconAlpha = 0.5f
        ))
    }

    if (state) {
        model.value = TabModel(
            width = size,
            corner = corner,
            alpha = 0.2f,
            titleWidth = ((size - 26.dp) / 2), // title width = ( tab width -icon size ) / 2
            iconAlpha = 1f
        )
    } else {
        model.value = TabModel(
            width = (size - 50.dp),
            corner = 25f,
            alpha = 0f,
            titleWidth = 0.dp,
            iconAlpha = 0.5f
        )
    }

    val widthAnimation by animateDpAsState(
        targetValue = model.value.width,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    val cornerAnimation by animateFloatAsState(
        targetValue = model.value.corner,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    val alphaAnimation by animateFloatAsState(
        targetValue = model.value.alpha,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    val iconAnimation by animateFloatAsState(
        targetValue = model.value.iconAlpha,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    val titleWidthAnimation by animateDpAsState(
        targetValue = model.value.titleWidth,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    Box {
        Card(
            shape = RoundedCornerShape(cornerAnimation),
            modifier = Modifier
                .size(widthAnimation, height)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onClick()
                },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = tabBackgroundColor.copy(alpha = alphaAnimation)),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BadgeIcon(backgroundColor = badgeBackgroundColor, value = badgeValue) {
                        Image(modifier = Modifier
                            .alpha(iconAnimation)
                            .size(26.dp),
                            painter = painterResource(id = id),
                            contentDescription = "")
                    }
                    Text(text = title,
                        style = titleStyle,
                        maxLines = 1,
                        color = titleColor,
                        modifier = Modifier
                            .width(titleWidthAnimation)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NavTab(
    @DrawableRes icon: Int,
    size: Dp,
    height: Dp = 50.dp,
    badgeValue: Int = 0,
    badgeBackgroundColor: Color,
    onClick: () -> Unit,
) {

    Card(
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .size((size - 50.dp), height)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        elevation = 0.dp,
    ) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BadgeIcon(backgroundColor = badgeBackgroundColor, value = badgeValue) {
                    Image(
                        modifier = Modifier.size(26.dp),
                        painter = painterResource(id = icon),
                        contentDescription = "")
                }

            }
        }
    }
}