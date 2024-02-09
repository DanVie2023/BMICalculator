package com.viedan.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Class Variables are called 'fields'
    // private - only useable in this class
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private TextView resultText;
    private EditText ageEditText;
    private EditText heightEditText;
    private EditText weightEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();

    }

    private void findViews(){
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        heightEditText = findViewById(R.id.edit_text_height);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBMI();

                if (ageEditText.getText().toString().length() != 0) {
                    String ageText = ageEditText.getText().toString();
                    int age = Integer.parseInt(ageText);

                    if (age >= 18) {
                        displayResult(bmiResult);
                    } else {
                        displayGuidance(bmiResult);
                    }
                } else {
                    displayResult(0);
                }
            }
        });
    }

    private double calculateBMI() {

        String heightText = heightEditText.getText().toString();
        String weightText = weightEditText.getText().toString();
        //Converting String into Int
        int height = Integer.parseInt(heightText);
        int weight = Integer.parseInt(weightText);

        return weight / ((0.01*height)*(0.01*height));
    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;

        if (bmi == 0){
            // Fehlerausgabe
            fullResultString = "Please fill all forms";
        } else if(bmi > 25) {
            //Display overweight
            fullResultString = bmiTextResult + " - You are overweight";
        } else if (bmi < 18.5){
            // Fehlerausgabe
            fullResultString = "Please fill all forms";
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        } else {
            //Display healthy
            fullResultString = bmiTextResult + " - You are healthy weight";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;

        if (maleButton.isChecked()) {
            // Display Boy Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for boys";
        } else if (femaleButton.isChecked()) {
            // Display Girl Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for girls";
        } else {
            //Display general Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(fullResultString);
    }


}