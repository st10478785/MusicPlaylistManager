package vcmsa.ci.musicplaylistmanager

// Vishay Gosai
// ST10478785

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Arrays to store item details
    private val songTitle = mutableListOf<String>()
    private val artistsName = mutableListOf<String>()
    private val ratingBar = mutableListOf<Float>()
    private val comments = mutableListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Get references to UI components
        val edtSongTitle = findViewById<EditText>(R.id.edtSongTitle)
        val edtArtistsName = findViewById<EditText>(R.id.edtArtistsName)
        val edtRatingBar = findViewById<RatingBar>(R.id.edtRatingBar)
        val edtComments = findViewById<EditText>(R.id.edtComments)
        val btnAddItem = findViewById<Button>(R.id.btnAddItem)
        val btnNext = findViewById<Button>(R.id.btnNext)

        // Add Item Button Click Listener
        btnAddItem.setOnClickListener {
            val songTitle = edtSongTitle.text.toString()
            val artistsName = edtArtistsName.text.toString()
            val ratingBar = edtRatingBar.rating.toString()
            val comment = edtComments.text.toString()

            try {
                // Validate inputs
                if (songTitle.isBlank() || artistsName.isBlank() || ratingBar.isBlank()) {
                    throw IllegalArgumentException("All fields except comments must be filled!")
                }

                val ratingBar = ratingBar.toIntOrNull()
                    ?: throw NumberFormatException("Quantity must be a number!")

                // Add item details to arrays
                addItem(songTitle, artistsName, ratingBar, comment)

                // Clear input fields
                edtSongTitle.text.clear()
                edtArtistsName.text.clear()
                // edtRatingBar.rating.clear()                not working
                edtRatingBar.rating = 0f
                edtComments.text.clear()

                Toast.makeText(this, "Song Titles added successfully!", Toast.LENGTH_SHORT).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this,
                    "Invalid rating! Please enter a valid rating.",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "An unexpected error occurred: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Next Button Click Listener
        btnNext.setOnClickListener {
            if (songTitle.isEmpty()) {
                Toast.makeText(
                    this,
                    "No Song Titles to display. Please add a song title first!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Pass data to ViewListActivity
                val intent = Intent(this, DetailedViewScreenActivity::class.java)
                intent.putStringArrayListExtra("Song Title", ArrayList(songTitle))
                intent.putStringArrayListExtra("Artist's Name", ArrayList(artistsName))
                // Please add ratingBar
                intent.putFloatArrayListExtra("Rating", ArrayList(ratingBar))
                intent.putStringArrayListExtra("comments", ArrayList(comments))
                startActivity(intent)
            }
        }
    }

    // Function to add item details to parallel arrays
    private fun addItem(songTitle: String, artistsName: String, ratingBar: Int, comment: String) {
        this.songTitle.add(songTitle)
        this.artistsName.add(artistsName)
        this.ratingBar.add(ratingBar.toFloat())
        comments.add(comment)
    }
}

private fun Any.putFloatArrayListExtra(s: String, arrayList: ArrayList<Float>) {

}
