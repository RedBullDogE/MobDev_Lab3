package ua.kpi.comsys.iv7213.views


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class ChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val path = Path()
    private val oval = RectF()

    private var centerCoordinateX: Float = 0f
    private var centerCoordinateY: Float = 0f
    private val px = 30f // Accuracy - px per unit

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        centerCoordinateX = width / 2f
        centerCoordinateY = height / 2f

        val innerSpaceRadius = 150f
        val chartRadius = 300f


        paint.style = Paint.Style.FILL
        paint.color = Color.parseColor("#ffea00")
        oval.set(
            centerCoordinateX - chartRadius,
            centerCoordinateY - chartRadius,
            centerCoordinateX + chartRadius,
            centerCoordinateY + chartRadius
        )

        canvas?.drawArc(oval, 0f, 54f, true, paint)

        paint.color = Color.parseColor("#0dff00")
        oval.set(
            centerCoordinateX - chartRadius,
            centerCoordinateY - chartRadius,
            centerCoordinateX + chartRadius,
            centerCoordinateY + chartRadius
        )

        canvas?.drawArc(oval, 54f, 90f, true, paint)

        paint.color = Color.GRAY
        oval.set(
            centerCoordinateX - chartRadius,
            centerCoordinateY - chartRadius,
            centerCoordinateX + chartRadius,
            centerCoordinateY + chartRadius
        )

        canvas?.drawArc(oval, 144f, 180f, true, paint)

        paint.color = Color.parseColor("#ff2f00")
        oval.set(
            centerCoordinateX - chartRadius,
            centerCoordinateY - chartRadius,
            centerCoordinateX + chartRadius,
            centerCoordinateY + chartRadius
        )

        canvas?.drawArc(oval, 324f, 36f, true, paint)

        // center white space
        paint.color = Color.WHITE

        paint.strokeWidth = 5f
        paint.style = Paint.Style.FILL // заливаем


        oval.set(
            centerCoordinateX - innerSpaceRadius,
            centerCoordinateY - innerSpaceRadius,
            centerCoordinateX + innerSpaceRadius,
            centerCoordinateY + innerSpaceRadius
        )
        canvas?.drawArc(oval, 0f, 360f, true, paint)
    }
}