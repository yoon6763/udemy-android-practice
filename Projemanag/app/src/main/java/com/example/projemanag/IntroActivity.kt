package com.example.projemanag

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.projemanag.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private val binding by lazy { ActivityIntroBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.run {

            btnSignInIntro.setOnClickListener {
                startActivity(Intent(this@IntroActivity, SignInActivity::class.java))
            }


            btnSignUpIntro.setOnClickListener {
                startActivity(Intent(this@IntroActivity, SignUpActivity::class.java))
            }
        }
    }
}