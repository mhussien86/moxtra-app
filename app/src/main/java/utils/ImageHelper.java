package utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class ImageHelper {
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        // final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        // paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static int pxToDp(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        return width;
    }

    /**
     * @param imageview that will be rotated
     * @param degree    of rotation may be 90 or 180 and so on
     */
    public static void rotate(ImageView imageview, float degree) {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, degree, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        imageview.startAnimation(rotateAnim);
    }

    public static class BitmapLoader {
        public static int getScale(int originalWidth, int originalHeight, final int requiredWidth,
                                   final int requiredHeight) {
            // a scale of 1 means the original dimensions
            // of the image are maintained
            int scale = 1;

            // calculate scale only if the height or width of
            // the image exceeds the required value.
            if ((originalWidth > requiredWidth) || (originalHeight > requiredHeight)) {
                // calculate scale with respect to
                // the smaller dimension
                if (originalWidth < originalHeight)
                    scale = Math.round((float) originalWidth / requiredWidth);
                else
                    scale = Math.round((float) originalHeight / requiredHeight);

            }

            return scale;
        }

        public static BitmapFactory.Options getOptions(String filePath, int requiredWidth, int requiredHeight) {

            BitmapFactory.Options options = new BitmapFactory.Options();
            // setting inJustDecodeBounds to true
            // ensures that we are able to measure
            // the dimensions of the image,without
            // actually allocating it memory
            options.inJustDecodeBounds = true;
            options.inScaled = false;
            // decode the file for measurement
            BitmapFactory.decodeFile(filePath, options);

            // obtain the inSampleSize for loading a
            // scaled down version of the image.
            // options.outWidth and options.outHeight
            // are the measured dimensions of the
            // original image
            options.inSampleSize = getScale(options.outWidth, options.outHeight, requiredWidth, requiredHeight);

            // set inJustDecodeBounds to false again
            // so that we can now actually allocate the
            // bitmap some memory
            options.inJustDecodeBounds = false;

            return options;

        }

        public static BitmapFactory.Options getOptions(String filePath) {

            BitmapFactory.Options options = new BitmapFactory.Options();
            // setting inJustDecodeBounds to true
            // ensures that we are able to measure
            // the dimensions of the image,without
            // actually allocating it memory
            options.inJustDecodeBounds = true;
            options.inScaled = false;
            // decode the file for measurement
            BitmapFactory.decodeFile(filePath, options);

            // obtain the inSampleSize for loading a
            // scaled down version of the image.
            // options.outWidth and options.outHeight
            // are the measured dimensions of the
            // original image
//			options.inSampleSize = getScale(options.outWidth, options.outHeight, requiredWidth, requiredHeight);

            // set inJustDecodeBounds to false again
            // so that we can now actually allocate the
            // bitmap some memory
            options.inJustDecodeBounds = false;

            return options;

        }

        public static Bitmap loadBitmap(String filePath, int requiredWidth, int requiredHeight) {

            BitmapFactory.Options options = getOptions(filePath, requiredWidth, requiredHeight);

            return BitmapFactory.decodeFile(filePath, options);
        }

        public static Bitmap loadBitmap(String filePath) {

            BitmapFactory.Options options = getOptions(filePath);

            return BitmapFactory.decodeFile(filePath, options);
        }
    }

}
