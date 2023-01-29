package com.shageldi.androidlessons.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.widget.Toast;

import com.shageldi.androidlessons.Common.SimUtil;
import com.shageldi.androidlessons.R;

import java.util.List;

public class SmsSender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_sender);
        sendSMS("+99363430338","Shallama");
//        SimUtil.sendSMS(this, 0, "+99363430338", null, "Sent by SIM1", null, null);
    }

    public void sendSMS2(final String number, final String text) {

        if (Build.VERSION.SDK_INT >= 22) {

            SubscriptionManager subscriptionManager = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                subscriptionManager = ((Activity) this).getSystemService(SubscriptionManager.class);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                SubscriptionInfo subscriptionInfo = subscriptionManager.getActiveSubscriptionInfoForSimSlotIndex(0);
                SmsManager.getSmsManagerForSubscriptionId(subscriptionInfo.getSubscriptionId()).sendTextMessage(number, null, text, null, null);
                SmsManager.getSmsManagerForSubscriptionId(subscriptionInfo.getSubscriptionId()).sendTextMessage(number, null, text, null, null);

            }
          }
        }

    private void sendSMS(String phoneNo, String msg) {
        try {
            System.out.println(phoneNo);
            System.out.println(msg);
            System.out.println("By hte codes");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE}, 1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_NUMBERS},2);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                System.out.println("this is first");
                SubscriptionManager localSubscriptionManager = SubscriptionManager.from(getApplicationContext());
//
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                            1);
                }
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_PHONE_STATE)){
                }
                System.out.println(localSubscriptionManager.getActiveSubscriptionInfoCount());
                System.out.println("this is count of SIM");
                if (localSubscriptionManager.getActiveSubscriptionInfoCount() > 1) {
                    System.out.println("this is third");
                    List localList = localSubscriptionManager.getActiveSubscriptionInfoList();
                    SubscriptionInfo simInfo1 = (SubscriptionInfo) localList.get(0);
                    SubscriptionInfo simInfo2 = (SubscriptionInfo) localList.get(1);
                    //SendSMS From SIM One
                    SmsManager.getSmsManagerForSubscriptionId(simInfo1.getSubscriptionId()).sendTextMessage(phoneNo, null, "msg+SIM 1", null, null);
                    //SendSMS From SIM Two
                    SmsManager.getSmsManagerForSubscriptionId(simInfo2.getSubscriptionId()).sendTextMessage(phoneNo, null, "msg+SIM 2", null, null);
                }
            }
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(this, "SMS sent", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Sms",ex.getMessage());
            Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private  int getBatteryLevel(){
        int batteryLevel = -1;
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        }else{
            Intent intent= new ContextWrapper(getApplicationContext()).registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)*100)/intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        }
        return  batteryLevel;
    }
}