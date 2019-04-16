package com.lovely.pig.vector_drawable

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun second(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    fun third(view: View) {
        startActivity(Intent(this, ThirdActivity::class.java))
    }

    fun fourth(view:View){
        startActivity(Intent(this, FourthActivity::class.java))
    }

}
