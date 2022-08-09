package com.s1dan.android_lab_61

import android.content.res.Resources
import android.graphics.*
import android.view.SurfaceHolder
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import com.s1dan.android_lab_6.R


class DrawThread(private val surfaceHolder: SurfaceHolder, resources: Resources?) :
    Thread() {
    private var runFlag = false
    private val picture: Bitmap
    private val matrix: Matrix
    fun setRunning(run: Boolean) {
        runFlag = run
    }

    override fun run() {
        var canvas: Canvas?
        while (runFlag) {
            try {
                sleep(50)
            } catch (e: InterruptedException) {
            }
            matrix.preRotate(2.0f, (picture.width / 2).toFloat(),
                (picture.height / 2).toFloat())
            canvas = null
            try {
                canvas = surfaceHolder.lockCanvas(null)
                synchronized(surfaceHolder) {
                    canvas.drawColor(Color.WHITE)
                    canvas.drawBitmap(picture, matrix, null)
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

    init {
        picture = BitmapFactory.decodeResource(resources, R.drawable.rabbit_10)
        matrix = Matrix()
        matrix.postScale(1.0f, 1.0f)
        matrix.postTranslate(10.0f, 10.0f)
    }
}