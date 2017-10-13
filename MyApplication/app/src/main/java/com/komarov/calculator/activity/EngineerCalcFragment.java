package com.komarov.calculator.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.komarov.calculator.R;

public class EngineerCalcFragment extends Fragment {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonPercent, buttonDot, buttonClear, buttonBackpace, buttonDiv, buttonMul, buttonAdd, buttonSub;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_engineer_calc, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        initBasicButtons(view);

        return view;
    }


    private void initBasicButtons(View view) {
        button0 = view.findViewById(R.id.buttonZero);
        button1 = view.findViewById(R.id.buttonOne);
        button2 = view.findViewById(R.id.buttonTwo);
        button3 = view.findViewById(R.id.buttonThree);
        button4 = view.findViewById(R.id.buttonFour);
        button5 = view.findViewById(R.id.buttonFive);
        button6 = view.findViewById(R.id.buttonSix);
        button7 = view.findViewById(R.id.buttonSeven);
        button8 = view.findViewById(R.id.buttonEight);
        button9 = view.findViewById(R.id.buttonNine);
        buttonPercent = view.findViewById(R.id.buttonPercent);
        buttonDot = view.findViewById(R.id.buttonDot);
        buttonClear = view.findViewById(R.id.buttonClear);
        buttonBackpace = view.findViewById(R.id.buttonBackspace);
        buttonDiv = view.findViewById(R.id.buttonDiv);
        buttonMul = view.findViewById(R.id.buttonMul);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonSub = view.findViewById(R.id.buttonSubtract);

        textInput = view.findViewById(R.id.txtInput);
        textResult = view.findViewById(R.id.txtSolution);
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
    }

}
