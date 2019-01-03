package com.khch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khch.androidshapes.R
import com.khch.animator.AnimatorActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val intent = Intent(this, AnimatorActivity::class.java)
        startActivity(intent)
    }
}
