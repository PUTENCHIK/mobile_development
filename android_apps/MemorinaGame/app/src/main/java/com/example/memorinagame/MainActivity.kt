package com.example.memorinagame

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    val images = mapOf(
        "barash" to R.drawable.barash,
        "ezhik" to R.drawable.ezhik,
        "karich" to R.drawable.karich,
        "kopatich" to R.drawable.kopatich,
        "krosh" to R.drawable.krosh,
        "losyash" to R.drawable.losyash,
        "pin" to R.drawable.pin,
        "sovunya" to R.drawable.sovunya,
    )
    var correctAnswers: Int = 0
    val n: Int = 4
    var openedCard: ImageView? = null
    var openedCardsAmount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(30)

        val tvCorrectAnswers = TextView(applicationContext)
        tvCorrectAnswers.setId(R.id.tvCorrectAnswers)
        tvCorrectAnswers.textSize = 22f

        val field = LinearLayout(applicationContext)
        field.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1f

        val imageViews = ArrayList<ImageView>()
        for (character in images) {
            for (i in 1..2) {
                imageViews.add(
                    ImageView(applicationContext).apply {
                        setImageResource(R.drawable.ic_launcher_background)
                        layoutParams = params
                        tag = character.key
                        setOnClickListener(cardListener)
                    }
                )
            }
        }

        val rows = Array(4, { LinearLayout(applicationContext) })
        var count = 0
        for (view in imageViews) {
            val row: Int = count / n
            rows[row].addView(view)
            count++
        }
        for (row in rows) {
            field.addView(row)
        }

        layout.addView(tvCorrectAnswers)
        layout.addView(field)
        setContentView(layout)
    }

    fun updateCorrectAnswers() {
        var tvCorrectAnswers = findViewById<TextView>(R.id.tvCorrectAnswers)
        tvCorrectAnswers.text = "Correct answers: " + correctAnswers.toString()
    }

    suspend fun checkAnswers(card: View) {
        changeCardStatus(card, true)
        when (openedCardsAmount) {
            0 -> {
                if (card is ImageView) {
                    openedCard = card
                    openedCardsAmount++
                }
            }
            1 -> {
                openedCardsAmount++
                delay(1500)
                if (card.tag == openedCard?.tag) {
                    correctAnswers++
                    hideCard(card)
                    hideCard(openedCard!!)
                } else {
                    changeCardStatus(card, false)
                    changeCardStatus(openedCard!!, false)
                }
                openedCardsAmount -= 2
                openedCard = null
            }
            else -> {
                var a: Int
            }
        }

    }

    suspend fun changeCardStatus(card: View, open: Boolean) {
        if (card is ImageView) {
            card.setImageResource(when (open) {
                    true -> images.get(card.tag)!!
                    false -> R.drawable.ic_launcher_background
                }
            )
            card.isClickable = !open
        }
    }

    suspend fun hideCard(card: View) {
        if (card is ImageView) {
            card.visibility = View.INVISIBLE
            card.isClickable = false
        }
    }

    val cardListener = View.OnClickListener() {
        GlobalScope.launch(Dispatchers.Main)
        { checkAnswers(it) }

        updateCorrectAnswers()
    }
}