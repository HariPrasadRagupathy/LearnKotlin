import android.graphics.Shader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin

// Define your custom shape
class UniqueShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            cubicTo(
                size.width * 0.65f, size.height * 0.75f,
                size.width * 0.35f, size.height * 1.25f,
                0f, size.height
            )
            close()
        }
        return Outline.Generic(path)
    }
}

// The main composable function
@Composable
fun DrawCustomShape(content : @Composable () -> Unit) {
    val space : Dp = 50.dp
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(intrinsicSize = IntrinsicSize.Max)
        .background(Color.White)) {
        // The first Box for expanding content

        // The second Box for drawing the shape, aligned to the bottom
      /*  Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .height(100.dp) // Adjust the height to fit the curve
            .drawBehind {
                drawUniqueShape(this)
            })*/
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            //.padding(bottom = space)
            .drawBehind {

                drawUniqueShape(this, height = space.toPx())
            }
           // .background(Color(0xFF00CACE))
        ) {
            Column(){
               content()

                Spacer(modifier = Modifier.height(space/2))
            }
        }
    }
    Spacer(modifier = Modifier.height(space/2))
}

// Function to draw the custom shape using DrawScope
fun drawUniqueShape(drawScope: DrawScope, height: Float = 100f) {


    val colors = listOf(Color(0xFF00CACE),Color(0x00FFFFFF))
    val angle = 40f // 45 degrees

// Calculate the start and end offsets based on the angle
    val x0 = 0f
    val y0 = 0f
    val x1 =drawScope.size.width
    val y1 = drawScope.size.height



    val linearGradient = Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset.Infinite,
        tileMode = TileMode.Clamp
    )

    val size = Size(drawScope.size.width, height)
    val path = Path().apply {
        moveTo(0f, 0f)
        lineTo(size.width, 0f)
        lineTo(size.width, drawScope.size.height)
        //lineTo(0f, drawScope.size.height)
        cubicTo(
            size.width * 0.65f, drawScope.size.height-height,
            size.width * 0.35f, drawScope.size.height+height,
            0f, drawScope.size.height
        )
        close()
    }

    drawScope.drawPath(path = path, brush =linearGradient, style = Fill)
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomShape() {
    Column() {
        DrawCustomShape(){
            Text("Hello", modifier = Modifier.padding(16.dp))
            Text("Hello", modifier = Modifier.padding(16.dp))
            Text("Hello", modifier = Modifier.padding(16.dp))
            Text("Hello", modifier = Modifier.padding(16.dp))
        }
        Text("Hello Bottom")
        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface {
            PreviewCustomShape()
        }
    }
}
