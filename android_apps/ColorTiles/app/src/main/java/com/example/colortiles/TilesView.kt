package com.example.colortiles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.random.Random


class TilesView(context: Context, var N: Int): View(context) {
    public var clicks: Int = 0

    private var h: Float = 1000f
    private var w: Float = 1000f
    private val n: Int = N
    private val margin: Int = 50
    private val padding: Int = 3
    private val paint = Paint()
    private val gridPaint = Paint()

    private val matrix: Array<BooleanArray> = Array(n){ BooleanArray(n){ false } }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        h = (bottom - top).toFloat()
        w = (right - left).toFloat()
        gridPaint.color = Color.rgb(40, 40, 40)

        for (i in 0..n-1) {
            for (j in 0..n-1) {
                matrix[i][j] = getRandomState()
            }
        }
    }

    fun getRandomState(): Boolean {
        return Random.nextBoolean()
    }

    fun getRandomColor(state: Boolean): Int {
        return if (state) Color.WHITE else Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, w, w, gridPaint)

        for (i in 0..n-1) {
            for (j in 0..n-1) {
                paint.color = getRandomColor(matrix[i][j])
                canvas.drawRect(
                    j * w/n + padding,
                    i * w/n + padding,
                    (j+1) * w/n - padding,
                    (i+1) * w/n - padding,
                    paint)
            }
        }
    }

    fun invertRow(index: Int, except: Int) {
        for (j in 0..n-1) {
            if (j != except) {
                matrix[index][j] = !matrix[index][j]
            }
        }
    }

    fun invertColumn(index: Int, except: Int) {
        for (i in 0..n-1) {
            if (i != except) {
                matrix[i][index] = !matrix[i][index]
            }
        }
    }

    fun checkGameOver() {
        val value: Boolean = matrix[0][0]
        for (i in 0..n-1) {
            for (j in 0..n-1) {
                if (matrix[i][j] != value)
                    return
            }
        }
        val toast: Toast = Toast.makeText(context, "Game Over!", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        clicks++

        val i: Int = (event?.y!! / w * n).toInt()
        val j: Int = (event.x / w * n).toInt()

        if (i < n && j < n) {
            matrix[i][j] = !matrix[i][j]
            invertRow(i, j)
            invertColumn(j, i)
            invalidate()
        }
        checkGameOver()

        return super.onTouchEvent(event)
    }
}