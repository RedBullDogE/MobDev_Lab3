package ua.kpi.comsys.iv7213.views


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.pow


class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val xPoints = arrayListOf<Float>()
    private val path = Path()

    private var centerCoordinateX: Float = 0f
    private var centerCoordinateY: Float = 0f
    private val px = 30f // Accuracy - px per unit

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        centerCoordinateX = width / 2f
        centerCoordinateY = height / 2f

        // Canvas settings
        paint.style = Paint.Style.STROKE;

        drawCoordSystem(canvas)
        drawGraph(canvas)
    }

    private fun drawCoordSystem(canvas: Canvas?) {
        val coordPath = Path()
        paint.color = Color.GRAY
        paint.strokeWidth = 2f


        // Coordinate lines
        coordPath.moveTo(centerCoordinateX, 10f)
        coordPath.lineTo(centerCoordinateX, height - 10f)
        coordPath.moveTo(10f, centerCoordinateY)
        coordPath.lineTo(width - 10f, centerCoordinateY)

        // Arrows
        coordPath.moveTo(centerCoordinateX - 25, 45f)
        coordPath.lineTo(centerCoordinateX, 10f)
        coordPath.lineTo(centerCoordinateX + 25, 45f)
        coordPath.moveTo(width - 45f, centerCoordinateY - 25)
        coordPath.lineTo(width - 10f, centerCoordinateY)
        coordPath.lineTo(width - 45f, centerCoordinateY + 25)

        // Coordinate marks
        coordPath.moveTo(centerCoordinateX + px, centerCoordinateY + 10f)
        coordPath.lineTo(centerCoordinateX + px, centerCoordinateY - 10f)
        coordPath.moveTo(centerCoordinateX + 10f, centerCoordinateY - px)
        coordPath.lineTo(centerCoordinateX - 10f, centerCoordinateY - px)

        // X interval
        coordPath.moveTo(centerCoordinateX + 5 * px, centerCoordinateY + 10f)
        coordPath.lineTo(centerCoordinateX + 5 * px, centerCoordinateY - 10f)
        coordPath.moveTo(centerCoordinateX - 5 * px, centerCoordinateY + 10f)
        coordPath.lineTo(centerCoordinateX - 5 * px, centerCoordinateY - 10f)

        // Y = 25 border
        coordPath.moveTo(centerCoordinateX + 10f, centerCoordinateY - 25 * px)
        coordPath.lineTo(centerCoordinateX - 10f, centerCoordinateY - 25 * px)

        canvas?.drawPath(coordPath, paint)
    }

    private fun drawGraph(canvas: Canvas?) {
        paint.color = Color.BLACK
        paint.strokeWidth = 3f

        val graphPath = Path()


        for (n in 0..100) {
            xPoints.add((-5 + n * 0.1).toFloat())
        }

        val xStartGraph = xPoints[0]
        val yStartGraph = squareFunction(xStartGraph)

        var convertedDots =
            convertCoordinates(xStartGraph, yStartGraph, centerCoordinateX, centerCoordinateY, px)
        graphPath.moveTo(convertedDots[0], convertedDots[1])


        for (i in (1 until xPoints.size)) {
            convertedDots = convertCoordinates(
                xPoints[i],
                squareFunction(xPoints[i]),
                centerCoordinateX,
                centerCoordinateY,
                px
            )
            graphPath.lineTo(convertedDots[0], convertedDots[1])
        }

        canvas?.drawPath(graphPath, paint)
    }
}

fun squareFunction(x: Float): Float {
    return x.pow(2)
}

fun convertCoordinates(x: Float, y: Float, xCenter: Float, yCenter: Float, px: Float): ArrayList<Float> {
    return arrayListOf(xCenter - x * px, yCenter - y * px)
}