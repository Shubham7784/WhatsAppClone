package com.app.whatsappclone.presentation.updateScreen

import androidx.collection.objectListOf
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.ArrayList

@Composable
fun UpdateCardOther(
    updateDataModel: UpdateDataModel,
    ringThickness: Dp = 4.dp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .size(100.dp,160.dp)
        // use default Material3 card styling, customize if needed
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Avatar with ring
            Box(
                modifier = Modifier
                    .size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                // Draw gradient ring behind the avatar if isNew
                if (updateDataModel.isNew) {
                    StatusRing(
                        size = 60.dp,
                        thickness = ringThickness,
                        colors = objectListOf<Color>( Color(0xFF33CC33), Color(0xFF00AEEF), Color(0xFFFFD700)).asList()
                    )
                }

                // Avatar image - local drawable
                Image(
                    painter = painterResource(id = updateDataModel.avatarRes),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = updateDataModel.name,modifier= Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Medium, color = Color.Black)
        }
    }
}

/**
 * Draws a circular gradient ring using Canvas.
 * @param size overall size of the ring container
 * @param thickness stroke thickness
 * @param colors list of colors to form a sweep-like gradient
 */
@Composable
private fun StatusRing(
    size: Dp,
    thickness: Dp,
    colors: List<Color>,
) {
    Canvas(modifier = Modifier.size(size)) {
        val stroke = thickness.toPx()
        val radius = (size.toPx() - stroke) / 2f
        val center = Offset(size.toPx() / 2f, size.toPx() / 2f)

        // Use a sweep-like gradient by painting an arc with a linear brush approximating sweep.
        // Compose doesn't provide SweepGradient directly in stable APIs, but Brush.sweepGradient
        // may be available in your Compose version. If available, you can replace Brush.linearGradient with
        // Brush.sweepGradient(colors, center = center) for a perfect sweep effect.
        val brush = Brush.linearGradient(
            colors = colors,
            start = Offset(0f, 0f),
            end = Offset(size.toPx(), size.toPx())
        )

        drawArc(
            brush = brush,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = Offset(
                (size.toPx() - radius * 2f) / 2f,
                (size.toPx() - radius * 2f) / 2f
            ),
            size = Size(radius * 2f, radius * 2f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = stroke,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
