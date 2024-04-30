package cz.mandr.dartscounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import cz.mandr.dartscounter.screens.MainActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val duration: Long = 1500

        val image: ImageView = findViewById(R.id.image_darts)
        val text1: TextView = findViewById(R.id.textview1)
        val text2: TextView = findViewById(R.id.textview2)
        image.alpha = 0f
        text1.alpha = 0f
        text2.alpha = 0f
        text1.animate().setDuration(duration).alpha(1f)
        text2.animate().setDuration(duration).alpha(1f)
        image.animate().setDuration(duration).alpha(1f).withEndAction{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}