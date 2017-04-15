package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by USER on 07/04/2015.
 */
public class ImageUtil {

    // get the base 64 string
    public static String convertImageToBase64(String imagePath) {

        File imageFile = new File(imagePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        String imgString = Base64.encodeToString(stream.toByteArray(),
                Base64.NO_WRAP);

        return imgString;
    }

    // get the base 64 bitmap
    public static String convertImageToBase64(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap. compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream . toByteArray();
        return Base64. encodeToString(byteArray, Base64.NO_WRAP);
    }
    public static String convertToBase64(String imagePath) {

        // decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        o.inScaled = false;
        o.inDither = true;
        o.inPreferredConfig = Bitmap.Config.ARGB_8888;

        final int REQUIRED_SIZE = 250;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        o2.inScaled = false;
        o2.inDither = true;
        o2.inPreferredConfig = Bitmap.Config.ARGB_8888;

        String imgString = null;

        File file = new File(imagePath);
        FileInputStream stream2 = null;
        try {
            stream2 = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream);
            imgString = Base64.encodeToString(stream.toByteArray(),
                    Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgString;

//        InputStream inputStream = null ;//You can get an inputStream using any IO API
//        try {
//            inputStream = new FileInputStream(imagePath);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes;
//        byte[] buffer = new byte[8192];
//        int bytesRead;
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        try {
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                output.write(buffer, 0, bytesRead);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        bytes = output.toByteArray();
//        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static String getByteArrayFromImage(String filePath) {

        File file = new File(filePath);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum);
                //no doubt here is 0
                /*Writes len bytes from the specified byte array starting at offset
                off to this byte array output stream.*/
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            Log.d("error", "error");
        }
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes,
                Base64.DEFAULT);
    }

}
