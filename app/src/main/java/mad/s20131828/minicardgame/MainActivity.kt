package mad.s20131828.minicardgame

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    //create var for background music named bgMusic as null
    private var bgMusic: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide() //hide action bar only with phone status bar still enable
        //Import BGM to MediaPlayer object inside bgMusic
        bgMusic = MediaPlayer.create(this, R.raw.bgm)
    }

    override fun onResume() {
        super.onResume()
        bgMusic?.start() //Play Music once app is onResume status
    }

    override fun onPause() {
        super.onPause()
        //Pause the music if app is inactive and kill the bgm by its logic
        //when restart button is click
        bgMusic?.pause()
    }

    //Function of restart game button
    fun restartGame(v:View){
        finish()
        startActivity(intent)
    }

    //create flip card sound effect
    private fun cardFlipPlay(){
        val cardFlip = MediaPlayer.create(this, R.raw.flipcardsound)
        cardFlip.start()
    }

    //Default Game related numbers and array
    private var totalPoints = 0 //var of Total Points earned
    private var roundNumber = 0 //var of how many rounds have been run
    //Array of cards in String form
    private val cardDisplay = arrayOf("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    //Array of cards for their ImageView form
    private val showCardImage = arrayOf(
        R.drawable.card_ace,
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card6,
        R.drawable.card7,
        R.drawable.card8,
        R.drawable.card9,
        R.drawable.card10,
        R.drawable.card_j,
        R.drawable.card_q,
        R.drawable.card_k
    )

    //create action and run game after click the Play button
    @SuppressLint("SetTextI18n")
    fun generateNextCard(v:View) {
        //Import ImageView and TextView status into var
        val card1TV = findViewById<ImageView>(R.id.card1TV)
        val card2TV = findViewById<ImageView>(R.id.card2TV)
        val pointTV = findViewById<TextView>(R.id.pointTV)
        val roundNumberTV = findViewById<TextView>(R.id.roundNumberTV)
        val cardGroupStatus = findViewById<TextView>(R.id.cardGroupStatus)
        val pointTextRefect = findViewById<TextView>(R.id.pointTextRefect)
        val drawCard1Action = Random.nextInt(0, 13)
        val drawCard2Action = Random.nextInt(0, 13)

        //Play card flipping sound effect
        cardFlipPlay()

        //Card Change method
        card1TV.setImageResource(showCardImage[drawCard1Action])
        card2TV.setImageResource(showCardImage[drawCard2Action])

        //different points give out when specific cards combination meets
        if (drawCard1Action == 0 && drawCard2Action == 0){
            totalPoints += 50
            cardGroupStatus.text = "One Pair of " + cardDisplay[drawCard1Action]
            pointTextRefect.text = "Wins 50 Points"
        }else if ((drawCard1Action in 1..8) && drawCard2Action == drawCard1Action) {
            totalPoints += 5
            cardGroupStatus.text = "One Pair of " + cardDisplay[drawCard1Action]
            pointTextRefect.text = "Wins 5 Points"
        }else if (drawCard1Action == 9 && drawCard2Action == 9){
            totalPoints += 10
            cardGroupStatus.text = "One Pair of " + cardDisplay[drawCard1Action]
            pointTextRefect.text = "Wins 10 Points"
        }else if ((drawCard1Action in 10..12) && drawCard2Action == drawCard1Action){
                totalPoints += 20
                cardGroupStatus.text = "One Pair of " + cardDisplay[drawCard1Action]
                pointTextRefect.text = "Wins 20 Points"
            }else if ((drawCard1Action == 0 || drawCard2Action == 0) && drawCard1Action != drawCard2Action){
                totalPoints += 2
                cardGroupStatus.text = "One " + cardDisplay[0]
                pointTextRefect.text = "Wins 2 Points"
            }else{
                totalPoints -= 1
                cardGroupStatus.text = ""
                pointTextRefect.text = "Loses 1 Point"
        }

        roundNumber += 1 //add 1 round per click on button
        pointTV.text = totalPoints.toString()
        roundNumberTV.text = roundNumber.toString()

    }
}