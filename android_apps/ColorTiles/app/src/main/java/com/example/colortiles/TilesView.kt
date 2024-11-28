package com.example.colortiles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import androidx.core.view.setPadding
import kotlin.random.Random
import kotlin.random.nextUInt

class TilesView(context: Context): View(context) {
    // onDraw
    // onTouch
    private var h: Float = 1000f
    private var w: Float = 1000f
    private val n: Int = 4
    private val padding: Int = 3
    private val paint = Paint()
    private val gridPaint = Paint()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        h = (bottom - top).toFloat()
        w = (right - left).toFloat()
        gridPaint.color = Color.rgb(40, 40, 40)
    }

    fun getRandomColor(): Int {
        return if (Random.nextInt(0, 2) == 0)
                    Color.BLACK
                else
                    Color.WHITE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, w, w, gridPaint)

        for (i in 0..n-1) {
            for (j in 0..n-1) {
                paint.color = getRandomColor()
                canvas.drawRect(
                    j * w/n + if (j == 0) padding else padding,
                    i * w/n + if (i == 0) padding else padding,
                    (j+1) * w/n - if (j == n-1) padding else padding,
                    (i+1) * w/n - if (i == n-1) padding else padding,
                    paint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.x

        return super.onTouchEvent(event)
    }
}