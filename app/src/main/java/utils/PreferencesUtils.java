package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;


/**
 * Created by mohamed hussien on 5/7/2014.
 */
public class PreferencesUtils {
    private static final String TAG = "PreferencesUtils";
    protected Context context;
    private static PreferencesUtils instance;
//    private PreferencesUtils preferences;

    public static void saveToSharedPreferences(String preferenceName, String preferenceValue , Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(preferenceName, preferenceValue);
        prefsEditor.commit();
        Log.i(TAG, "Preference: "+ preferenceName+ " with value "+ preferenceValue+" was successfully saved to preferences");
    }
    public static String getSharedPreference (String preferenceName , Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String preferenceValue = appSharedPrefs.getString(preferenceName, "");
        Log.i(TAG, "Preference: "+ preferenceName+ " with value "+ preferenceValue+" was successfully retrieved from preferences");
        return preferenceValue;
    }

    public static void saveBooleanToSharedPreferences(String preferenceName, Boolean preferenceValue , Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putBoolean(preferenceName, preferenceValue);
        prefsEditor.commit();
        Log.i(TAG, "Preference: "+ preferenceName+ " with value "+ preferenceValue+" was successfully saved to preferences");
    }
    public static Boolean getBooleanSharedPreference (String preferenceName , Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        Boolean preferenceValue = appSharedPrefs.getBoolean(preferenceName, false);
        Log.i(TAG, "Preference: "+ preferenceName+ " with value "+ preferenceValue+" was successfully retrieved from preferences");
        return preferenceValue;
    }

    public static void clearAllSharedPreferences(Context context){

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.clear().apply();
    }


    //mohamed hussien code to be a singletone class

    public static synchronized PreferencesUtils getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesUtils();
        }
        instance.context = context;
        return instance;
    }

    // Boolean
    public boolean getBoolean(int entryResId) {
        return getBoolean(context.getString(entryResId), false);
    }

    public boolean getBoolean(String entry) {
        return getBoolean(entry, false);
    }

    public boolean getBoolean(int entryResId, boolean defaultValue) {
        return getBoolean(context.getString(entryResId), defaultValue);
    }

    public boolean getBoolean(String entry, boolean defaultValue) {
        return getSharedPreferences().getBoolean(getEncryptedName(entry), defaultValue);
    }

    public void setBoolean(int entryResId, boolean value) {
        setBoolean(context.getString(entryResId), value);
    }

    public void setBoolean(String entry, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getEncryptedName(entry), value);
        editor.commit();
    }

    public void setInteger(String entry, int value) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(entry, value);
        editor.commit();
    }


    public int getInteger(String key){

        SharedPreferences sharedPreferences = getSharedPreferences();
        int value = sharedPreferences.getInt(key, 0);
        return value ;
    }


//    // String
//    public String getString(int entryResId) {
//        return getString(context.getString(entryResId), null);
//    }


    public String getString(String entry) {
        SharedPreferences appSharedPrefs = getSharedPreferences();
        String value = appSharedPrefs.getString(getEncryptedName(entry),null);
        return value;
    }
//    public String getString(int entryResId, String defaultValue) {
//        return getString(context.getString(entryResId), defaultValue);
//    }


//    public String getString(String entry, String defaultValue) {
//        String value = getSecurePreferences().getString(getEncryptedName(entry));
//        if (value != null) {
//            return value;
//        } else {
//            return defaultValue;
//        }
//    }

//    public void setString(int entryResId, String value) {
//        setString(context.getString(entryResId), value);
//    }

    public void setString(String entry, String value) {
        SharedPreferences appSharedPrefs = getSharedPreferences();
        SharedPreferences.Editor editor = appSharedPrefs.edit();
        editor.putString(getEncryptedName(entry), value);
        editor.commit();

    }

    public void removeString(int entryResId) {
        removeString(context.getString(entryResId));
    }

    public void removeString(String entry) {
        getSecurePreferences().removeValue(getEncryptedName(entry));
    }

    private SecurePreferences getSecurePreferences() {
        return new SecurePreferences(context, getEncryptedName(context.getApplicationContext().getPackageName()),
                getEncryptedName(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)), true);
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
//		return context.getApplicationContext().getPreferences(Context.MODE_PRIVATE);
    }


    private String getEncryptedName(String name) {
        return new StringBuffer(StringUtils.md5(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)
                + context.getApplicationContext().getPackageName() + name)).reverse().toString();
    }

//    public PreferencesUtils getPreferences() {
//        return preferences;
//    }


}
