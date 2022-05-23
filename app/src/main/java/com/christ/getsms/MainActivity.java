package com.christ.getsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtview;
    private String stringNumber = "0000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_SMS,
                        Manifest.permission.SEND_SMS},
                PackageManager.PERMISSION_GRANTED);

        txtview = findViewById(R.id.textView);
    }


    public void buttonF(View view){

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null, null, null );
        cursor.moveToFirst();

        while(cursor!=null){
            String stringmessage = cursor.getString(12);

            if(stringmessage.contains("programmer")){
                SmsManager smsManagerSend = SmsManager.getDefault();
                smsManagerSend.sendTextMessage(stringNumber, null, stringmessage, null, null);
                Toast.makeText(getApplicationContext(),"Message envoye",Toast.LENGTH_LONG).show();
                //txtview.setText("message");
                break;
            }

            Toast.makeText(getApplicationContext(),"Message not found",Toast.LENGTH_LONG).show();
            cursor.moveToNext();
        }
    }




}