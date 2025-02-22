package com.example.bmicalculator2

import android.annotation.SuppressLint
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import java.util.TimerTask
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val weight=findViewById<EditText>(R.id.weight)
        val height=findViewById<EditText>(R.id.height)
        val btn=findViewById<Button>(R.id.result)
        val res=findViewById<TextView>(R.id.BMI)

        btn.setOnClickListener{

            var txt=""
            val weightValue = weight.text.toString().toFloatOrNull() ?: 0f
            val heightValue = height.text.toString().toFloatOrNull() ?: 0f
            Log.i("MY_TAG","$weightValue")
            if(weightValue>0f || heightValue>0f){
                var result=weightValue/((heightValue/100)*(heightValue/100))
                txt = when {
                    result < 16.0 -> "Underweight (Severe thinness) 😟"
                    result < 17.0 -> "Underweight (Moderate thinness) 😕"
                    result < 18.5 -> "Underweight (Mild thinness) 😐"
                    result < 25.0 -> "Normal range 🙂"
                    result < 30.0 -> "Overweight (Pre-obese) 😮"
                    result < 35.0 -> "Obese (Class I) 😕"
                    result < 40.0 -> "Obese (Class II) 😟"
                    else -> "Obese (Class III) 😢"

                }
                val speedometerView = findViewById<ImageView>(R.id.speedometerView)


                res.setText("Your BMI is: %.2f\n$txt".format(result))
                val speedometerDrawable = ContextCompat.getDrawable(this, R.drawable.final_drawable) as LayerDrawable
                val speedValue = (result / 40 * 100).toInt() // Normalize BMI to 0-100 scale
                SpeedometerHelper.moveSpeedometer(speedometerDrawable, speedometerView, speedValue, 100)
                speedometerView.visibility= VISIBLE
                res.visibility= VISIBLE

            }
            else{
                Toast.makeText(this, "Please Fill in the Details", Toast.LENGTH_SHORT).show()

            }


        }


    }


}
