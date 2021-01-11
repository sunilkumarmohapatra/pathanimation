package com.sunil.pathanimation

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class PathView : View {
    lateinit var path: Path
    lateinit var paint: Paint
    var length = 0f

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    fun init() {
        paint = Paint()
        paint.color = Color.WHITE
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        path = Path()
        path.moveTo(180f, 400f)
        path.lineTo(280f, 100f)
        path.lineTo(880f, 100f)
        path.lineTo(880f, 100f)
        path.lineTo(980f, 400f)
        path.lineTo(180f, 400f)
        path.lineTo(180f, 800f)
        path.lineTo(980f, 800f)
        path.lineTo(980f, 400f)
        path.moveTo(520f, 800f)
        path.lineTo(520f, 600f)
        path.lineTo(720f, 600f)
        path.lineTo(720f, 800f)
        path.moveTo(540f, 800f)
        path.lineTo(540f, 620f)
        path.lineTo(700f, 620f)
        path.lineTo(700f, 800f)
        path.moveTo(240f, 480f)
        path.lineTo(340f, 480f)
        path.lineTo(340f, 540f)
        path.lineTo(240f, 540f)
        path.lineTo(240f, 480f)
        path.moveTo(280f, 100f)
        path.lineTo(400f, 400f)
        path.moveTo(480f, 100f)
        path.lineTo(600f, 400f)
        path.moveTo(680f, 100f)
        path.lineTo(800f, 400f)
        path.moveTo(315f, 200f)
        path.lineTo(910f, 200f)
        path.moveTo(360f, 300f)
        path.lineTo(940f, 300f)
        path.moveTo(400f, 400f)
        path.lineTo(400f, 800f)

        // Measure the path
        val measure = PathMeasure(path, false)
        length = measure.length
        val intervals = floatArrayOf(length, length)
        val animator = ObjectAnimator.ofFloat(this@PathView, "phase", 1.0f, 0.0f)
        animator.duration = 5000
        animator.start()
    }

    fun setPhase(phase: Float) {
        Log.d("pathview", "setPhase called with:$phase")
        paint?.pathEffect = createPathEffect(
            length,
            phase,
            0.0f
        )
        invalidate()
    }

    public override fun onDraw(c: Canvas) {
        super.onDraw(c)
        c.drawPath(path!!, paint!!)
    }

    companion object {
        private fun createPathEffect(
            pathLength: Float,
            phase: Float,
            offset: Float
        ): PathEffect {
            return DashPathEffect(
                floatArrayOf(pathLength, pathLength),
                Math.max(phase * pathLength, offset)
            )
        }
    }
}