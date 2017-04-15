package utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;


public class ConnectionUtils {
	/**
	 * function returning boolean indicating if connection is via wifi or not
	 * 
	 * @return boolean whether connection is wifi or not
	 */
	public static boolean hasWifi(Context context) {
		boolean res = false;
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		if (netInfo != null) {
			if (netInfo.getTypeName().equalsIgnoreCase("mobile")) {
				res = false;
			} else {
				res = true;
			}
		}
		return res;
	}

	public static boolean isRoaming(Context context) {
		TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephony.isNetworkRoaming();
	}
	
	
	public static boolean hasInternet(Context context){
		
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getActiveNetworkInfo();
		  if (ni == null) {
		   // There are no active networks.
		   return false;
		  } else{
		   return true;
		  }
	}
}
