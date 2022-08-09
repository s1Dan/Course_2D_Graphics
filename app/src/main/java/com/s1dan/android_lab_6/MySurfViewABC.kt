package com.s1dan.android_lab_61

import android.content.Context
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceViewABC(context: Context?) : SurfaceView(context),
    SurfaceHolder.Callback {
    private var drawThread: DrawThread? = null
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
    override fun surfaceCreated(holder: SurfaceHolder) {
        drawThread = DrawThread(getHolder(), resources)
        drawThread!!.setRunning(true)
        drawThread!!.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        drawThread!!.setRunning(false)
        while (retry) {
            try {
                drawThread!!.join()
                retry = false
            } catch (e: InterruptedException) {
            }
        }
    }

    init {
        holder.addCallback(this)
    }
}