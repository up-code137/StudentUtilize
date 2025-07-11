package com.example.layoutspractice

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class recordedLectures : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recorded_lectures)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val class9 = findViewById<Button>(R.id.rl9)
        class9.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@UnacademyClass9and10"))
            startActivity(intent)
        }
        val class10 = findViewById<Button>(R.id.rl10)
        class10.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@UnacademyClass9and10"))
            startActivity(intent)
        }
        val class11s = findViewById<Button>(R.id.rl11s)
        class11s.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@khanacademyindia/playlists"))
            startActivity(intent)
        }
        val class11c = findViewById<Button>(R.id.rl11c)
        class11c.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@vedantucommerce4201/playlists"))
            startActivity(intent)
        }
        val class11a = findViewById<Button>(R.id.rl11A)
        class11a.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@UnacademyHumanities/playlists"))
            startActivity(intent)
        }
        val class12s = findViewById<Button>(R.id.rl12s)
        class12s.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@LearnoHubClass1112/playlists"))
            startActivity(intent)
        }
        val class12c = findViewById<Button>(R.id.rl12c)
        class12c.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@Commerce.edu.AHSEC.course/playlists"))
            startActivity(intent)
        }
        val class12a = findViewById<Button>(R.id.rl12A)
        class12a.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@MagnetBrainsEducation/playlists"))
            startActivity(intent)
        }



    }
}