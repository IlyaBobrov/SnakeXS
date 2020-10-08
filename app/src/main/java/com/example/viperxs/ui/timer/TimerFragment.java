package com.example.viperxs.ui.timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.viperxs.R;

public class TimerFragment extends Fragment implements View.OnClickListener{

    //TODO: реализовать функционал таймера из книги

    private TimerViewModel timerViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_timer, container, false);
        final TextView textView = root.findViewById(R.id.text_timer);
        timerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@NonNull String s) {
                textView.setText(s);
            }
        });

        Button button_start_timer = (Button) root.findViewById(R.id.button_start_timer);
        button_start_timer.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {

    }
}