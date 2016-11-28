package br.com.heiderlopes.androidservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.heiderlopes.androidservice.boundservice.BoundActivity;
import br.com.heiderlopes.androidservice.broadcasteservice.BroadcastServiceActivity;
import br.com.heiderlopes.androidservice.downloadimageservice.DownloadImageActivity;
import br.com.heiderlopes.androidservice.foregroundservice.ForegroundActivity;
import br.com.heiderlopes.androidservice.intentservice.IntentServicesActivity;
import br.com.heiderlopes.androidservice.simplesplayerservice.PlayerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirForegroundService(View v) {
        startActivity(new Intent(this, ForegroundActivity.class));
    }

    public void abrirBroadcastService(View v) {
        startActivity(new Intent(this, BroadcastServiceActivity.class));
    }

    public void abrirBoundService(View v) {
        startActivity(new Intent(this, BoundActivity.class));
    }

    public void abrirIntentService(View v) {
        startActivity(new Intent(this, IntentServicesActivity.class));
    }

    public void abrirDownloadImagem(View v) {
        startActivity(new Intent(this, DownloadImageActivity.class));
    }

    public void abrirPlayer(View v) {
        startActivity(new Intent(this, PlayerActivity.class));
    }
}
