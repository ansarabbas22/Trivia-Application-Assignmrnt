package com.rapidzz.tirivia_application_assignment.view.activities

import android.content.Intent
import android.os.Handler
import com.rapidzz.tirivia_application_assignment.R

class SplashActivity : BaseActivity() {
    companion object {
        val SPLASH_DELAY: Long = 3000
    }

    override fun initViews() {
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },
            SPLASH_DELAY
        )
    }

    override fun getLayoutId(): Int = R.layout.activity_splash


}
