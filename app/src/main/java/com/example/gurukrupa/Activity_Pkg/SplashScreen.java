package com.example.gurukrupa.Activity_Pkg;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.gurukrupa.R;

public class SplashScreen extends AppCompatActivity {

    private boolean connected = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        netstatus();


  }

    private void netstatus() {

        inetcheck();
        if (connected){
            continueExe();

        }else{
            NoinetAlertDailog();

        }

    }

    private void continueExe() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                finish();
            }
        }).start();
    }

    public void inetcheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected= true;
        } else {
            connected= false;
        }
    }

    public void NoinetAlertDailog() {
        AlertDialog.Builder builder =new AlertDialog.Builder(SplashScreen.this);
        builder.setCancelable(false);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Connection not available. Kindly check your connectivity");
        builder.setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(SplashScreen.this,SplashScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });
//        builder.setNeutralButton("Open", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                startActivityForResult(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS), 0);
//            }
//        });
        builder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }

}
