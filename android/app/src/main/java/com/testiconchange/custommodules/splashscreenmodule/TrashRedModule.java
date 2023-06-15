package com.testiconchange;

import androidx.annotation.NonNull;
import android.content.Intent;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.os.Bundle;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.widget.Toast;


public class TrashRedModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public TrashRedModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
        @NonNull
        public String getName() {
            return "TrashRedModule";
        }


//         @ReactMethod
//         public void setNewActivity() {
//             final Activity activity = getCurrentActivity();
//             Intent mass = new Intent(activity,TrashRedActivity.class);
//             this.reactContext.startActivity(mass);
//         }


}
