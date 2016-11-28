package br.com.heiderlopes.androidservice.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import br.com.heiderlopes.androidservice.R;
import br.com.heiderlopes.androidservice.foregroundservice.Constantes;

public class IntentServicesActivity extends AppCompatActivity {


    private TextView tvResult;
    private IntentServiceResultReceiver intentServiceResultReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_services);
        tvResult = (TextView) findViewById(R.id.tvResult);

        Intent textCapitalizeIntent= new Intent(this, MeuIntentService.class);
        textCapitalizeIntent.putExtra(MeuIntentService.INPUT_TEXT, "Download: ");
        startService(textCapitalizeIntent);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(intentServiceResultReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        registerReceiver();
        super.onResume();
    }

    private void registerReceiver() {
        IntentFilter intentFilter =new IntentFilter(IntentServiceResultReceiver.ACTION);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        intentServiceResultReceiver = new IntentServiceResultReceiver();
        registerReceiver(intentServiceResultReceiver, intentFilter);
    }

    public class IntentServiceResultReceiver extends BroadcastReceiver {

        public final static String ACTION= "br.com.heiderlopes.intent.action.ACTION";

        @Override
        public void onReceive(Context context, Intent intent) {
            String resultText =intent.getStringExtra(MeuIntentService.OUTPUT_TEXT);
            tvResult.setText(resultText);
        }
    }
}