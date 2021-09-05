package mad.s20131828.minicardgame

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bgmPlay() //run background music if the app alive
    }

    //create background music function
    fun bgmPlay() {
        var bgMusic = MediaPlayer.create(this, R.raw.bgm)
        bgMusic.isLooping = true
        bgMusic.start()
    }

    //create flip card sound effect
    fun cardFlipPlay(){
        var cardFlip = MediaPlayer.create(this, R.raw.flipcardsound)
        cardFlip.start()
    }

    //Default Game related numbers and array
    var totalPoints = 0
    var roundNumber = 0
    val cardDisplay = arrayOf("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    val showCardImage = arrayOf(
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

    //Function of restart game button
    fun restartGame(v:View){
        finish()
        startActivity(getIntent())
    }

    //create action and run game after click the Play button
    fun generateNextCard(v: View) {
        var card1TV = findViewById<ImageView>(R.id.card1TV)
        var card2TV = findViewById<ImageView>(R.id.card2TV)
        var pointTV = findViewById<TextView>(R.id.pointTV)
        var roundNumberTV = findViewById<TextView>(R.id.roundNumberTV)
        var cardGroupStatus = findViewById<TextView>(R.id.cardGroupStatus)
        var pointTextRefect = findViewById<TextView>(R.id.pointTextRefect)
        var drawCard1Action = Random.nextInt(0, 13)
        var drawCard2Action = Random.nextInt(0, 13)

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

        roundNumber += 1
        pointTV.text = totalPoints.toString()
        roundNumberTV.text = roundNumber.toString()

    }
}