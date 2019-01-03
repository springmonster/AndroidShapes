package com.khch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khch.androidshapes.MainActivity
import com.khch.androidshapes.R
import com.khch.animator.AnimatorActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        activity_start_shape_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        activity_start_animator_btn.setOnClickListener {
            val intent = Intent(this, AnimatorActivity::class.java)
            startActivity(intent)
        }
    }
}
