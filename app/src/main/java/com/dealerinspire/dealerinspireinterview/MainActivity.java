package com.dealerinspire.dealerinspireinterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dealerinspire.dealerinspireinterview.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final int UNDEFINED_ANGLE = 0;

    private ActivityMainBinding binding;
    private final Calendar customCalendar = getNowCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updateClockAndFields();
        binding.clock.setOnClickListener(view -> displayTimePickerDialog());
    }

    private void updateClockAndFields() {
        binding.clock.setTime(customCalendar);
        int hours = customCalendar.get(Calendar.HOUR);
        int minutes = customCalendar.get(Calendar.MINUTE);
        binding.hoursTextView.setText(getHoursString(hours));
        binding.minutesTextView.setText(getMinutesString(minutes));
        binding.angleTextView.setText(getAngleBetweenClockHands(hours, minutes));
    }

    private void displayTimePickerDialog() {
        MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(customCalendar.get(Calendar.HOUR))
                .setMinute(customCalendar.get(Calendar.MINUTE))
                .setTitleText("Select a time")
                .build();
        timePicker.addOnPositiveButtonClickListener(
                view -> {
                    customCalendar.set(Calendar.HOUR, timePicker.getHour());
                    customCalendar.set(Calendar.MINUTE, timePicker.getMinute());
                    updateClockAndFields();
                }
        );

        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    private Calendar getNowCalendar() {
        return Calendar.getInstance();
    }

    private String getHoursString(int hoursFromCalendar) {
        return getString(R.string.hours, hoursFromCalendar);
    }

    private String getMinutesString(int minutesFromCalendar) {
        return getString(R.string.minutes, minutesFromCalendar);
    }

    private String getAngleBetweenClockHands(int hours, int minutes) {
        //TODO: IMPLEMENT THE LOGIC TO DETERMINE THE ANGLE
        int angle = UNDEFINED_ANGLE;
        return getString(R.string.angle, angle);
    }
}