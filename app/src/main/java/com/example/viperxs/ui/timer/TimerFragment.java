package com.example.viperxs.ui.timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.viperxs.R;

public class TimerFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    //TODO: реализовать функционал таймера из книги
    private MalibuCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button button_start_timer;
    private Button button_pause_timer;
    private TextView text;
    private TextView timeElapsedView;
    private SeekBar seekbarBtnWeigth;

    private EditText editTextTime, editTextInterval;

    private final long mill = 1000;
    private long startTime;
    private long interval = 1 * 1000;


    LinearLayout.LayoutParams layoutParams1;
    LinearLayout.LayoutParams layoutParams2;

    private TimerViewModel timerViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_timer, container, false);
        final TextView textView = root.findViewById(R.id.text_timer);

        button_start_timer = (Button) root.findViewById(R.id.button_start_timer);
        button_start_timer.setOnClickListener(this);

        button_pause_timer = (Button) root.findViewById(R.id.button_pause_timer);
        button_pause_timer.setOnClickListener(this);

        text = (TextView) root.findViewById(R.id.timer);
        timeElapsedView = (TextView) root.findViewById(R.id.timeElapsed);

        editTextTime = (EditText) root.findViewById(R.id.editTextInputTimeForTimer);
        editTextInterval = (EditText) root.findViewById(R.id.editTextInputIntervalForTimer);

        //**seek bar**
        seekbarBtnWeigth = (SeekBar) root.findViewById(R.id.seekbarBtnWeigth);
        seekbarBtnWeigth.setOnSeekBarChangeListener(this);
        layoutParams1 = (LinearLayout.LayoutParams) button_start_timer.getLayoutParams();
        layoutParams2 = (LinearLayout.LayoutParams) button_pause_timer.getLayoutParams();
        //**

        editTextTime.setText("10");
        editTextInterval.setText("1");

        button_start_timer.setText("Запустить");
        button_pause_timer.setText("Пауза");

        timerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@NonNull String s) {
                text.setText(text.getText() + String.valueOf(startTime));
                textView.setText(s);
            }
        });

        return root;
    }

    private void setTime(){
        try {
            startTime = Integer.valueOf(String.valueOf(editTextTime.getText())) * mill;
        } catch (NumberFormatException e) {
            editTextTime.setText("0");
            e.printStackTrace();
        }
        try {
            interval = Integer.valueOf(String.valueOf(editTextInterval.getText())) * mill;
        } catch (NumberFormatException e) {
            editTextInterval.setText("1");
            e.printStackTrace();
        }

        countDownTimer = new MalibuCountDownTimer(startTime,interval);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start_timer:
                if (!timerHasStarted) {
                    setTime();
                    timerHasStarted = true;
                    countDownTimer.start();
                    button_start_timer.setText("Закончить");
                } else {
                    countDownTimer.cancel();
                    countDownTimer.onFinish();
                    timerHasStarted = false;
                    button_start_timer.setText("Начать заново");
                }
                break;
            case R.id.button_pause_timer:
                onPauseTime();
                break;
            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        int leftValue = progress;
        int rightValue = seekBar.getMax() - progress;
        layoutParams1.weight = leftValue;
        layoutParams2.weight = rightValue;

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public class MalibuCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MalibuCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        private long ost;
        @Override
        public void onTick(long millisUntilFinished) {
            /**Убираем погрешность
             * Пример
             * 1999 > 500 => 1000 - 999 = 1 => 1999 + 1
             * 2005 < 500 => 2005 - 5 = 2000
             */
            ost = (long) (millisUntilFinished % 1000);
            if (ost > 500) {
                millisUntilFinished += 1000 - ost;
            }
            if (ost < 500) {
                millisUntilFinished -= ost;
            }

            text.setText("Осталось: " + (millisUntilFinished));
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText("Прошло: " + String.valueOf(timeElapsed / mill));
        }

        @Override
        public void onFinish() {
            text.setText("Время вышло!");
            timeElapsed += mill;
            timeElapsedView.setText("Времени прошло: " + String.valueOf(timeElapsed / mill));
            timerHasStarted = false;
            button_start_timer.setText("Начать заново");
            startTime = 0;
        }




    }

    private long bufTime;
    private boolean pauseActive = false;
    private void onPauseTime() {
        if ((startTime - timeElapsed) > 0 ) {
            if (!pauseActive){
                bufTime = startTime - timeElapsed;
                countDownTimer.cancel();
                pauseActive = true;
            } else {
                countDownTimer = new MalibuCountDownTimer(bufTime, interval);
                countDownTimer.start();
                pauseActive = false;
            }
        }
    }
}