package com.shageldi.androidlessons.Service;

import android.net.Uri;
import android.os.Build;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CallScreeningService extends android.telecom.CallScreeningService {
    @Override
    public void onScreenCall(@NonNull Call.Details details) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Toast.makeText(this, "Test Jan: "+getNumberFromDetails(details), Toast.LENGTH_SHORT).show();
        }
//        respondToCall(details, new android.telecom.CallScreeningService.CallResponse.Builder().setSilenceCall(true));
    }

    private String getNumberFromDetails(@NonNull Call.Details details) {
        Uri handle = details.getHandle();
        if (handle == null) {
            Log.e("TAG", "No handle on incoming call");
            return null;
        }

        String scheme = handle.getScheme();
        if (scheme != null && scheme.equals("tel")) {
            return handle.getSchemeSpecificPart();
        }

        Log.e("TAG", "Unhandled scheme");
        return null;
    }
}
