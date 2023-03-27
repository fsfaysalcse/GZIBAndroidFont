package com.faysal.gzibfont

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.test_text)
        val typeface = loadCompressedFontFromResource(this, R.raw.font)

        typeface?.let {
            text.typeface = it
        }


    }
}