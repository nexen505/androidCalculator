package com.komarov.calculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    private EditText dialogText;
    private String dialogTextValue = "";
    private boolean isTextDialogOpened;
    private TextView textValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textValue = (TextView) findViewById(R.id.set_text);
    }

    public void onInputText(View view) {
        isTextDialogOpened = true;
        onShowTextDialog();
    }

    public void onShowTextDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(SettingsActivity.this);
        View promptView = layoutInflater.inflate(R.layout.text_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingsActivity.this);
        alertDialogBuilder.setView(promptView);

        dialogText = promptView.findViewById(R.id.edittext);
        dialogText.setText(dialogTextValue);

        dialogText.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER) {
                dialogTextValue = dialogText.getText().toString();
                return true;
            }
            return false;
        });
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton(R.string.button_ok,
                        (dialog, id) -> {
                            textValue.setText(dialogText.getText());
                            dialogTextValue = dialogText.getText().toString();
                            isTextDialogOpened = false;
                            dialog.cancel();
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
