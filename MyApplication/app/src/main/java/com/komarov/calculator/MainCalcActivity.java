package com.komarov.calculator;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

public class MainCalcActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonPercent, buttonDot, buttonClear, buttonBackpace, buttonDiv, buttonMul, buttonAdd, buttonSub, buttonResult;

    TextView textInput, textResult;

    private String getResourceText(int id) {
        String text = getResources().getString(id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(text).toString();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        button0 = (Button) findViewById(R.id.buttonZero);
        button1 = (Button) findViewById(R.id.buttonOne);
        button2 = (Button) findViewById(R.id.buttonTwo);
        button3 = (Button) findViewById(R.id.buttonThree);
        button4 = (Button) findViewById(R.id.buttonFour);
        button5 = (Button) findViewById(R.id.buttonFive);
        button6 = (Button) findViewById(R.id.buttonSix);
        button7 = (Button) findViewById(R.id.buttonSeven);
        button8 = (Button) findViewById(R.id.buttonEight);
        button9 = (Button) findViewById(R.id.buttonNine);
        buttonPercent = (Button) findViewById(R.id.buttonPercent);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonBackpace = (Button) findViewById(R.id.buttonBackspace);
        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSub = (Button) findViewById(R.id.buttonSubtract);
//        buttonResult = (Button) findViewById(R.id.buttonResult);

        textInput = (TextView) findViewById(R.id.txtInput);
        textResult = (TextView) findViewById(R.id.txtSolution);
        textInput.setText("");
        textResult.setText("");

        buttonClear.setOnClickListener(v -> {
            textInput.setText("");
        });

        buttonBackpace.setOnClickListener(v -> {
            String text = textInput.getText().toString();
            if (text.length() > 0) {
                textInput.setText(text.substring(0, text.length() - 1));
            }
        });

        button0.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_0));
        });

        button1.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_1));
        });

        button2.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_2));
        });

        button3.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_3));
        });

        button4.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_4));
        });

        button5.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_5));
        });

        button6.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_6));
        });

        button7.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_7));
        });

        button8.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_8));
        });

        button9.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_9));
        });

        button0.setOnClickListener(v -> {
            textInput.setText(textInput.getText() + getResourceText(R.string.button_number_0));
        });

    }
}
