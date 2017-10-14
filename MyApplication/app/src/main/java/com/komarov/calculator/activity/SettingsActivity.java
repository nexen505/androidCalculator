package com.komarov.calculator.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.komarov.calculator.R;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {
    private EditText dialogText;
    private String dialogTextValue = "", storedDate = "";
    private boolean isTextDialogOpened, isColorDialogOpened, isDateDialogOpened;
    private int selectedColor;
    private int year, month, day;
    private TextView textView, dateTextView;
    private Switch saveResultsSwitch;
    private DatePicker datePicker;
    private Button colorButton;
    private Spinner degreesSpinner;
    private SharedPreferences mSettings;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);

        saveResultsSwitch = (Switch) findViewById(R.id.result_switch);
        saveResultsSwitch.setChecked(false);
        textView = (TextView) findViewById(R.id.set_text);
        dateTextView = (TextView) findViewById(R.id.dateView);
        colorButton = (Button) findViewById(R.id.color_btn);
        degreesSpinner = (Spinner) findViewById(R.id.degrees_spinner);

        storedDate = Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year);
        dateTextView.setText(storedDate);

    }

    public void onInputText(View view) {
        isTextDialogOpened = true;
        dialogTextValue = textView.getText().toString();
        openTextDialog(dialogTextValue);
    }

    public void openTextDialog(String value) {
        LayoutInflater layoutInflater = LayoutInflater.from(SettingsActivity.this);
        View promptView = layoutInflater.inflate(R.layout.text_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingsActivity.this);
        alertDialogBuilder.setView(promptView);

        dialogTextValue = value;
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
                            textView.setText(dialogText.getText());
                            dialogTextValue = dialogText.getText().toString();
                            isTextDialogOpened = false;
                            dialog.cancel();
                        })
                .create().show();
    }

    public void onChooseColor(View view) {
        isColorDialogOpened = true;
        selectedColor = ((ColorDrawable) colorButton.getBackground()).getColor();
        openColorDialog(selectedColor);
    }

    private void openColorDialog(int initialColor) {
        ColorPickerDialogBuilder
                .with(SettingsActivity.this)
                .setTitle(R.string.colorpicker_title)
                .initialColor(initialColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(8)
                .setOnColorSelectedListener(selectedColor -> {
                    this.selectedColor = selectedColor;
                })
                .setPositiveButton(R.string.button_ok, (dialog, selectedColor, allColors) -> {
                    colorButton.setBackgroundColor(this.selectedColor);
                    isColorDialogOpened = false;
                })
                .setNegativeButton(R.string.button_cancel, (dialog, which) -> {
                    isColorDialogOpened = false;
                })
                .build()
                .show();
    }

    public void onChooseDate(View view) {
        isDateDialogOpened = true;
        openDateDialog(day, month, year);
    }

    public void openDateDialog(final int d, final int m, final int y) {
        LayoutInflater layoutInflater = LayoutInflater.from(SettingsActivity.this);
        View promptView = layoutInflater.inflate(R.layout.date_dialog, null);
        datePicker = promptView.findViewById(R.id.datePicker);
        datePicker.init(y, m, d, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingsActivity.this);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton(R.string.button_ok, (dialog, id) -> {
                    int day1 = datePicker.getDayOfMonth();
                    int month1 = datePicker.getMonth() + 1;
                    int year1 = datePicker.getYear();
                    storedDate = day1 + "." + month1 + "." + year1;
                    dateTextView.setText(storedDate);
                    isDateDialogOpened = false;
                    dialog.cancel();
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedColor", selectedColor);
        outState.putString("dialogTextValue", dialogTextValue);
        outState.putBoolean("saveResultsSwitch", saveResultsSwitch.isChecked());
        outState.putInt("degreesSpinner", degreesSpinner.getSelectedItemPosition());

        if (isDateDialogOpened && datePicker != null) {
            outState.putInt("day", datePicker.getDayOfMonth());
            outState.putInt("month", datePicker.getMonth());
            outState.putInt("year", datePicker.getYear());
        }

        outState.putBoolean("isTextDialogOpened", isTextDialogOpened);
        outState.putBoolean("isColorDialogOpened", isColorDialogOpened);
        outState.putBoolean("isDateDialogOpened", isDateDialogOpened);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            isDateDialogOpened = savedInstanceState.getBoolean("isDateDialogOpened", false);
            isTextDialogOpened = savedInstanceState.getBoolean("isTextDialogOpened", false);
            isColorDialogOpened = savedInstanceState.getBoolean("isColorDialogOpened", false);
            degreesSpinner.setSelection(savedInstanceState.getInt("degreesSpinner", 0));
            saveResultsSwitch.setChecked(savedInstanceState.getBoolean("saveResultsSwitch"));

            if (isColorDialogOpened) {
                selectedColor = savedInstanceState.getInt("selectedColor", ((ColorDrawable) colorButton.getBackground()).getColor());
                openColorDialog(selectedColor);
            } else if (isTextDialogOpened) {
                dialogTextValue = savedInstanceState.getString("dialogTextValue", dialogText.getText().toString());
                openTextDialog(dialogTextValue);
            } else if (isDateDialogOpened) {
                int savedDay = savedInstanceState.getInt("day");
                int savedMonth = savedInstanceState.getInt("month");
                int savedYear = savedInstanceState.getInt("year");
                openDateDialog(savedDay, savedMonth, savedYear);
            }
        }
    }
}
