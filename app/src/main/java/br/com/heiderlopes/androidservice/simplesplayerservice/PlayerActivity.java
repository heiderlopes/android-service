package br.com.heiderlopes.androidservice.simplesplayerservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.heiderlopes.androidservice.R;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btPlay, btStop;
    Intent playbackServiceIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btPlay = (Button) findViewById(R.id.btPlay);
        btStop = (Button) findViewById(R.id.btStop);

        btPlay.setOnClickListener(this);
        btStop.setOnClickListener(this);

        playbackServiceIntent = new Intent(this, PlayerService.class);
    }

    public void onClick(View v) {
        if (v == btPlay) {
            startService(playbackServiceIntent);
        } else if (v == btStop) {
            stopService(playbackServiceIntent);
        }
    }
}