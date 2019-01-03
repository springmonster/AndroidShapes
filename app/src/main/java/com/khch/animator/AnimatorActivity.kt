package com.khch.animator

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khch.androidshapes.R
import kotlinx.android.synthetic.main.activity_animator.*

class AnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)

        activity_animator_btn.setOnClickListener {
            createValueAnimator()
        }
    }

    private fun createValueAnimator() {
        val left: Int = activity_animator_tv.left
        val top: Int = activity_animator_tv.top
        val width: Int = activity_animator_tv.width
        val height: Int = activity_animator_tv.height

        val valueAnimator = ValueAnimator.ofInt(0, 30)
        valueAnimator.duration = 1000
        valueAnimator.addUpdateListener { it ->
            val value = it.animatedValue as Int

            activity_animator_tv.layout(left + value, top + value, width + value, height + value)
        }
        valueAnimator.start()
    }
}
