package com.example.helloworldapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var totalTimes = 0

        val btnClickMe = findViewById<Button>(R.id.button);
        val textView = findViewById<TextView>(R.id.textView);
        val editText = findViewById<EditText>(R.id.editText);

        btnClickMe.setOnClickListener {
            totalTimes += 1;
            //Toast.makeText(this, "You clicked me.", Toast.LENGTH_SHORT).show();
            textView.text = """Hello ${editText.text} you have clicked $totalTimes times""";
        }
    }

}