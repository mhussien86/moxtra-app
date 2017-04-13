package com.madroid.moxtraapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.madroid.moxtraapp.ui.login.LoginActivity;
import com.moxtra.sdk.MXAccountManager;
import com.moxtra.sdk.MXSDKConfig;

/**
 * Created by mohamed on 09/01/17.
 */
public class BaseActivity extends AppCompatActivity implements MXAccountManager.MXAccountUnlinkListener{




    @Override
    public void onUnlinkAccountDone(MXSDKConfig.MXUserInfo mxUserInfo) {

        Log.i("Unlink", "Unlinked moxtra account: " + mxUserInfo);
        if (mxUserInfo == null) {
            Log.e("Can't logout", "Can't logout: the mxUserInfo is null.");
            Toast.makeText(this, "unlink failed as mxUserInfo is null.", Toast.LENGTH_LONG).show();
        } else {
            Log.i("user", "User " + mxUserInfo.userIdentity + " logout OK.");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }



}
