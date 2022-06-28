package com.pbaileyapps.translatordemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText: EditText = findViewById(R.id.editText)
        val textView: TextView = findViewById(R.id.textView)
        val textTime: TextView = findViewById(R.id.textTime)
        val button: Button = findViewById(R.id.button)
        val translationConfigs = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.GERMAN)
            .build()
        val translator = Translation.getClient(translationConfigs)
        val defaultText = "What, if some day or night a demon were to steal after you into your loneliest loneliness and say to you: 'This life as you now live it and have lived it, you will have to live once more and innumerable times more'...Would you not throw yourself down and gnash your teeth and curse the demon who spoke thus? Or have you once experienced a tremendous moment when you would have answered him: 'You are a god and never have I heard anything more divine.'"
        button.setOnClickListener {
            val begin = System.currentTimeMillis()
            if (editText.text.isNotEmpty()) {
                translator.downloadModelIfNeeded()
                    .addOnSuccessListener {
                        Toast.makeText(this, "Download Successful", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                translator.translate(editText.text.toString())
                    .addOnSuccessListener {
                        textView.setText(it)
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                    }
            } else {
                translator.downloadModelIfNeeded()
                    .addOnSuccessListener {
                        Toast.makeText(this, "Download Successful", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                translator.translate(defaultText)
                    .addOnSuccessListener {
                        textView.setText(it)
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                    }
            }
            val end = System.currentTimeMillis()
            textTime.setText("Elapsed time: ${end-begin} milliseconds")
        }
    }
}