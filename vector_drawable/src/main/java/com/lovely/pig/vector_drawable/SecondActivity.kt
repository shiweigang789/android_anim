

package com.lovely.pig.vector_drawable

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.View
import android.widget.ImageView

class SecondActivity : AppCompatActivity() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun anim(view: View) {
        val imageView = view as ImageView
        val drawable = imageView.drawable
        if (drawable is Animatable) {
            drawable.start()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animL(view: View) {
        val imageView = view as ImageView
        val drawable = getDrawable(R.drawable.fivestar_anim) as AnimatedVectorDrawable
        imageView.setImageDrawable(drawable)
        drawable.start()
    }

}
