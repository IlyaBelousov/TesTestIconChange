package com.testiconchange;

import androidx.annotation.NonNull;
import android.content.Intent;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.widget.Toast;


public class ChangeIconModule extends ReactContextBaseJavaModule implements Application.ActivityLifecycleCallbacks {
    public static final String NAME = "IconChanger";
    private final String packageName = "com.testiconchange";
    private List<String> classesToKill = new ArrayList<>();
    private Boolean iconChanged = false;
    private String componentClass = "";
    private final ReactApplicationContext reactContext;

    public ChangeIconModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    @NonNull
    public String getName() {
        return this.NAME;
    }

    @ReactMethod
    public void getIcon(Promise promise){
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.reject("ACTIVITY_NOT_FOUND");
            return;
        }
        if (this.componentClass.isEmpty()) {
            this.componentClass = activity.getComponentName().getClassName();
        }
        String currentIcon = this.componentClass.split("MainActivity")[1];
        promise.resolve(currentIcon.isEmpty() ? "default" : currentIcon);
        return;
    }

    @ReactMethod
    public void changeIcon(String enableIcon, Promise promise) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.reject("ACTIVITY_NOT_FOUND");
            return;
        }
        if (this.componentClass.isEmpty()) {
            this.componentClass = activity.getComponentName().getClassName();
        }
        final String activeClass = this.packageName + "." + enableIcon;

        try {
            activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(this.packageName, activeClass),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            );

            if(enableIcon=="TrashRed"){
                PackageManager pm = activity.getPackageManager();
                pm.setComponentEnabledSetting(
                new ComponentName(activity, Trashred.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP
                );
            }
            if(enableIcon=="Trashtransp"){
                PackageManager pm = activity.getPackageManager();
                pm.setComponentEnabledSetting(
                new ComponentName(activity, Trashtransp.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP
                );
            }

            Toast.makeText( this.reactContext,"Enabling " + enableIcon,Toast.LENGTH_SHORT).show();
            promise.resolve(enableIcon);
        } catch (Exception e) {
            promise.reject("ICON_INVALID" + e);
            return;
        }
        this.classesToKill.add(this.componentClass);
        this.componentClass = activeClass;
        activity.getApplication().registerActivityLifecycleCallbacks(this);
        this.iconChanged = true;
    }

    private void completeIconChange() {
        if (!this.iconChanged) return;
        final Activity activity = getCurrentActivity();
        if (activity == null) return;
        classesToKill.forEach((cls) -> activity.getPackageManager().setComponentEnabledSetting(
            new ComponentName(this.packageName, cls),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        ));
        classesToKill.clear();
        this.iconChanged = false;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        completeIconChange();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
