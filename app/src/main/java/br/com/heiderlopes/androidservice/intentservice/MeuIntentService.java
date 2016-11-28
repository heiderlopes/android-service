package br.com.heiderlopes.androidservice.intentservice;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

public class MeuIntentService extends IntentService {

    private static final String TAG =MeuIntentService.class.getSimpleName();

    public static final String INPUT_TEXT="INPUT_TEXT";
    public static final String OUTPUT_TEXT="OUTPUT_TEXT";

    public MeuIntentService() {
        super(MeuIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"onHandleIntent()");

        String data =intent.getStringExtra(INPUT_TEXT);

        Log.d(TAG,data);

        data = data.toUpperCase();

        SystemClock.sleep(4 * 1000);

        data = data + " \n processado em : " + DateFormat.format("hh:mm sss",
                System.currentTimeMillis());

        Intent resultBroadCastIntent =new Intent();
        resultBroadCastIntent.setAction(IntentServicesActivity.IntentServiceResultReceiver.ACTION);
        resultBroadCastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        resultBroadCastIntent.putExtra(OUTPUT_TEXT, data);
        sendBroadcast(resultBroadCastIntent);
    }

}
