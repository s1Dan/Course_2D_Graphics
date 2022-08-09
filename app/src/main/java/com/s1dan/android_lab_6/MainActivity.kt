package com.s1dan.android_lab_6

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.s1dan.android_lab_61.MySurfaceViewABC

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    /*
    1.Разработайте приложение, содержащие различные объекты ShapeDrawable,
     анимированные с использованием анимации преобразованием (файл my_animation.xml)
     и кадровой анимации.

    2. Разработайте приложение, рисующее графику на объекте SurfaceView.
    */

    private lateinit var myImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myImageView = findViewById(R.id.ivMain)
        myImageView.setImageResource(R.drawable.shape)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_one -> {
                val animation1 = AnimationUtils.loadAnimation(this, R.anim.alpha)
                myImageView.startAnimation(animation1)
                return true
            }
            R.id.menu_two -> {
                val animation2 = AnimationUtils.loadAnimation(this, R.anim.rotate)
                myImageView.startAnimation(animation2)
                return true
            }
            R.id.menu_three -> {
                val animation3 = AnimationUtils.loadAnimation(this, R.anim.total)
                myImageView.startAnimation(animation3)
                return true
            }
            R.id.menu_SurfaceView -> {
                setContentView(MySurfaceViewABC(this))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAnimationStart(animation: Animation?) { myImageView.visibility = View.VISIBLE }

    override fun onAnimationEnd(animation: Animation?) { myImageView.visibility = View.INVISIBLE }

    override fun onAnimationRepeat(animation: Animation?) { myImageView.visibility = View.VISIBLE }
}