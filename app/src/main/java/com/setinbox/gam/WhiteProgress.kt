package com.setinbox.gam

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_white_progress.*

class WhiteProgress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_white_progress)

        val storylaneArray = mutableListOf(
            "You are Freedom, an android living in the the futuristic city of Zail. You have a laser pistol and a holoband. You are a security android created to provide military level force for corporations wealthy enough to purchase you. As you stand protecting a shipment you realize that your employers have left you unguarded and make your escape with the goods. You are now on the black market where everything is for sale.",
            "You decide to make a run for it and see what the world has to offer.",
            "You walk into a library, a small square glass building you find yourself in. The ceiling here is very high and the room contains hundreds or bookshelves.",
            "You start reading books, you find many interesting things. You learn that the 22nd century is a time of peace and prosperity. Wars are rare and people live to the age of 150 in average health.",
            "It's time you purchased some upgrades. They cost more than you normally would but there are always exceptions to the rule.",
            "Alright. You've got some cash, so you're going to pay 300 credits to enhance you pistol and holo-armor. But right after shopping some ditry bandits tried to rob you.",
            "That was the wrong choice. Your android life is over now."
        )

        val buttonStepsArray = mutableListOf(
            "Try to escape",
            "Go to the library",
            "Learn more about the city",
            "Find new components to upgrade",
            "Earn money and buy upgrades",
            "Lets fight with them",
            "Lets try again"
        )

        var i = -1

        textStroke(textView)
        textStroke(titleView)

        stepButton.setOnClickListener {
            if (i < 6) {
                i++
                textView.text = storylaneArray[i]
                stepButton.text = buttonStepsArray[i]
            } else {
                recreate()
            }
        }

    }

    private fun textStroke (textView: TextView) {
        textView.setTextColor(Color.WHITE)
        textView.setShadowLayer(1.6f,1.5f,1.3f,Color.BLACK)
    }

}