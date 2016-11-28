package br.com.heiderlopes.androidservice.downloadimageservice;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.heiderlopes.androidservice.R;

public class DownloadImageActivity extends AppCompatActivity implements OnClickListener {

    private String TAG = "TAG";

    private static final int REQUEST_WRITE_STORAGE = 112;

    EditText urlText;
    ProgressBar pd;
    ImageView imgView;
    SampleResultReceiver resultReceiever;
    String defaultUrl = "http://blogdalu.magazineluiza.com.br/wp-content/uploads/2016/03/Conhe%C3%A7as-algumas-das-novidades-do-Android-N.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
        resultReceiever = new SampleResultReceiver(new Handler());
        urlText = (EditText) findViewById(R.id.urlText);
        pd = (ProgressBar) findViewById(R.id.downloadPD);
        imgView = (ImageView) findViewById(R.id.imgView);

        checkPermission();
    }

    private class SampleResultReceiver extends ResultReceiver {
        public SampleResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            switch (resultCode) {
                case DownloadIntentService.DOWNLOAD_ERROR:
                    Toast.makeText(getApplicationContext(), "error in download",
                            Toast.LENGTH_SHORT).show();
                    pd.setVisibility(View.INVISIBLE);
                    break;

                case DownloadIntentService.DOWNLOAD_SUCCESS:
                    String filePath = resultData.getString("filePath");
                    Bitmap bmp = BitmapFactory.decodeFile(filePath);
                    if (imgView != null && bmp != null) {
                    imgView.setImageBitmap(bmp);
                    Toast.makeText(getApplicationContext(),
                            "image download via IntentService is done",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "error in decoding downloaded file",
                            Toast.LENGTH_SHORT).show();
                }
                pd.setIndeterminate(false);
                pd.setVisibility(View.INVISIBLE);

                break;
            }
            super.onReceiveResult(resultCode, resultData);
        }

    }

    @Override
    public void onClick(View v) {
        Intent startIntent = new Intent(this,
                DownloadIntentService.class);
        startIntent.putExtra("receiver", resultReceiever);
        startIntent.putExtra("url",
                TextUtils.isEmpty(urlText.getText()) ? defaultUrl : urlText
                        .getText().toString());
        startService(startIntent);
        pd.setVisibility(View.VISIBLE);
        pd.setIndeterminate(true);
    }




    private void checkPermission() {
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "Permissão para gravar negada");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("Necessária a permissao para ler o SD-Card")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("TAG", "Clicked");
                        requestPermission();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                requestPermission();
            }
        }
    }

    protected void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Permissão negada pelo usuário");
                } else {
                    Log.i(TAG, "Permissao concedida pelo usuario");
                }
                return;
            }
        }
    }
}