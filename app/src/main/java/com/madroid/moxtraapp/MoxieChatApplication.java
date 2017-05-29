package com.madroid.moxtraapp;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.moxtra.sdk.MXAccountManager;
import com.moxtra.sdk.MXSDKException;

/**
 * Created by mohamed on 31/01/17.
 */
public class MoxieChatApplication  extends MultiDexApplication {

    private static final String TAG = "MoxieChatApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            MXAccountManager.createInstance(this,null, null, true);
        } catch (MXSDKException.InvalidParameter invalidParameter) {
            Log.e(TAG, "Error when creating MXAccountManager instance.", invalidParameter);
        }
    }
}