package com.hyejeanmoon.spannablestringdemo

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val backgroundAndUnderline = findViewById<TextView>(R.id.backgroundAndUnderline)
        val foregroundAndStrikethrough = findViewById<TextView>(R.id.foregroundAndStrikethrough)
        val clickable = findViewById<TextView>(R.id.clickable)

        val baSpannable = SpannableString("Background and Underline")
        baSpannable.setSpan(
            BackgroundColorSpan(Color.YELLOW),
            0,
            11,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        baSpannable.setSpan(
            UnderlineSpan(),
            16,
            baSpannable.toString().length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        backgroundAndUnderline.text = baSpannable

        val faSpannable = SpannableString("Foreground and StrikeThrough")
        faSpannable.setSpan(
            ForegroundColorSpan(Color.GREEN),
            0,
            10,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        faSpannable.setSpan(
            StrikethroughSpan(),
            15,
            faSpannable.toString().length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        foregroundAndStrikethrough.text = faSpannable

        val clickableSpannable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@MainActivity, "hello", Toast.LENGTH_LONG).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.linkColor = Color.BLUE
                ds.underlineThickness = 0.5F
                ds.isUnderlineText = true
            }
        }
        val clickableSpannableString = SpannableString("clickable")
        clickableSpannableString.setSpan(
            clickableSpannable,
            0,
            clickableSpannableString.toString().length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        clickable.text = clickableSpannableString
        clickable.movementMethod = LinkMovementMethod.getInstance()

        val spannableStringBuilder = SpannableStringBuilder().also {
            it.append("hello world! click here!")
            it.setSpan(
                TextAppearanceSpan(this, R.style.style),
                0,
                12,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            it.setSpan(
                clickableSpannable,
                13,
                it.toString().length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            it.setSpan(
                TextAppearanceSpan(this, R.style.clickStyle),
                13,
                it.toString().length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView.text = spannableStringBuilder
        textView.movementMethod = LinkMovementMethod.getInstance()


    }
}
