package vcmsa.ci.musicplaylistmanager

// Vishay Gosai
// ST10478785

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        // UI Elements
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnExit = findViewById<Button>(R.id.btnExit)


        // Add Button to second screen
        btnAdd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // View Button to third screen
        btnView.setOnClickListener {
            val intent = Intent(this, DetailedViewScreenActivity::class.java)
            startActivity(intent)
        }

        // Exit Button to terminate the app
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}
