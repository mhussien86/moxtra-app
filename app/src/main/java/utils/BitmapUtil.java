package utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;

public class BitmapUtil {

	public static Bitmap downSizeBitmap(Bitmap bitmap,int reqSize)  {
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		 
		float scaleWidth = ((float) reqSize) / width;
		float scaleHeight = ((float) reqSize) / height;
		 
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		 
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
		return resizedBitmap;
		 
		/*if(bitmap.getWidth() < reqSize) {
			return bitmap;
		} else {
			return Bitmap.createScaledBitmap(bitmap, reqSize, reqSize, false);
		} */
	}
	
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static byte[] convertBitmapToBytes(Bitmap bitmap) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
	        bitmap.copyPixelsToBuffer(buffer);
	        return buffer.array();
      } else {
    	  	ByteArrayOutputStream baos = new ByteArrayOutputStream();  
    	  	bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	        byte[] data = baos.toByteArray();
	        return data;
      }
    }

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
