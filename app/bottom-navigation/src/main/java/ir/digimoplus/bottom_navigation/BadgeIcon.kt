package ir.digimoplus.bottom_navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BadgeIcon(
    backgroundColor: Color,
    value: Int,
    content: @Composable () -> Unit,
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(34.dp)) {

        content()

        Card(shape = RoundedCornerShape(50f),
            border = BorderStroke(1.dp, Color.White),
            backgroundColor = backgroundColor,
            modifier = Modifier
                .size(if (value == 0) 0.dp else 15.dp)
                .align(
                    Alignment.TopEnd)) {
            Text(modifier = Modifier.matchParentSize(),
                color = Color.White,
                fontSize = 10.sp,
                text = "$value",
                textAlign = TextAlign.Center)
        }

    }
}
