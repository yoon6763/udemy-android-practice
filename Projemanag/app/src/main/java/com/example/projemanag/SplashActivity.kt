package com.example.projemanag

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.projemanag.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val typeFace: Typeface = Typeface.createFromAsset(assets, "Ubuntu-Medium.ttf")
        binding.tvAppName.typeface = typeFace


        Handler().postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }, 2500)

    }
}