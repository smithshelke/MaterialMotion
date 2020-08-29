package com.smith.materialmotion

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}