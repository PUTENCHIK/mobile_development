package com.example.memorinagame

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    val back = R.drawable.back
    val n: Int = 4

    var correctAnswers: Int = 0
    var tries: Int = 0
    var openedCard: ImageView? = null
    var openedCardsAmount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout: LinearLayout = LinearLayout(applicationContext)
        layout.setId(R.id.mainLayout)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(30)

        val header = LinearLayout(applicationContext)
        header.orientation = LinearLayout.VERTICAL
        val headerParams = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        headerParams.setMargins(0, 0, 0, 20)
        header.layoutParams = headerParams

        val tvCorrectAnswers = TextView(applicationContext)
        tvCorrectAnswers.setId(R.id.tvCorrectAnswers)
        tvCorrectAnswers.textSize = 22f

        val tvTries = TextView(applicationContext)
        tvTries.setId(R.id.tvTries)
        tvTries.textSize = 22f

        val field = LinearLayout(applicationContext)
        field.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1f

        val imageViews = ArrayList<ImageView>()
        for (character in images) {
            for (i in 1..2) {
                imageViews.add(
                    ImageView(applicationContext).apply {
                        setImageResource(back)
                        layoutParams = params
                        tag = character.key
                        setOnClickListener(cardListener)
                    }
                )
            }
        }
        imageViews.shuffle()

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

        val btnReset = Button(applicationContext)
        btnReset.apply {
            text = "Заново"
            textSize = 22f
            width = LinearLayout.LayoutParams.MATCH_PARENT
            height = LinearLayout.LayoutParams.WRAP_CONTENT
            setOnClickListener(btnResetListener)
        }

        header.addView(tvCorrectAnswers)
        header.addView(tvTries)
        layout.addView(header)
        layout.addView(field)
        layout.addView(btnReset)
        setContentView(layout)
    }

    fun updateHeader() {
        var tvCorrectAnswers = findViewById<TextView>(R.id.tvCorrectAnswers)
        tvCorrectAnswers.text = "Пар найдено: " + correctAnswers.toString()

        var tvTries = findViewById<TextView>(R.id.tvTries)
        tvTries.text = "Попыток: " + tries.toString()
    }

    suspend fun checkAnswers(card: View) {
        when (openedCardsAmount) {
            0 -> {
                if (card is ImageView) {
                    openedCardsAmount++
                    changeCardStatus(card, true)
                    openedCard = card
                }
            }
            1 -> {
                openedCardsAmount++
                tries++
                changeCardStatus(card, true)
                if (card.tag == openedCard?.tag) {
                    correctAnswers++
                    updateHeader()
                    delay(1500)
                    hideCard(card)
                    hideCard(openedCard!!)
                    checkGameOver()
                } else {
                    updateHeader()
                    delay(1500)
                    changeCardStatus(card, false)
                    changeCardStatus(openedCard!!, false)
                }
                openedCardsAmount -= 2
                openedCard = null
            }
        }

    }

    fun changeCardStatus(card: View, open: Boolean) {
        if (card is ImageView) {
            card.setImageResource(when (open) {
                    true -> images.get(card.tag)!!
                    false -> back
                }
            )
            card.isClickable = !open
        }
    }

    fun hideCard(card: View) {
        if (card is ImageView) {
            card.visibility = View.INVISIBLE
            card.isClickable = false
        }
    }

    fun checkGameOver() {
        if (correctAnswers == 8) {
            val toast: Toast = Toast.makeText(applicationContext,
                "Вы нашли все пары!",
                Toast.LENGTH_LONG
            )
            toast.show()
        }
    }

    val cardListener = View.OnClickListener() {
        GlobalScope.launch(Dispatchers.Main)
        { checkAnswers(it) }
    }

    val btnResetListener = View.OnClickListener() {
        recreate()
    }
}