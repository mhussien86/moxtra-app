package utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FileUtils {
	public static File createTemporaryFile(String part, String ext) throws Exception {
		File tempDir = Environment.getExternalStorageDirectory();
		tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		return File.createTempFile(part, ext, tempDir);
	}
	
	/**
	 * read file from asset folder and return input stream
	 * @param filePath
	 */
	public static InputStream readFileFromAssetsWithInResult(Context context, String filePath) throws IOException{
		
		//read the content of asset file
		return context.getAssets().open(filePath);
	}
	
	
	/**
	 * read file from asset folder and return String
	 * @param filePath
	 */
	public static String readFileFromAssetsWithStringResult(Context context, String filePath) throws IOException{
		
		InputStream inputStream = readFileFromAssetsWithInResult(context, filePath);
		
		return getContent(inputStream);
		
	}
	

	
	/**
	 * read the content of inputstream
	 * @param inputStream
	 * @return
	 */
	public static String getContent(InputStream inputStream) throws IOException{
		
		//parse the response
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		
		while((line = reader.readLine()) != null){
			stringBuilder.append(line);
		}
		
		return stringBuilder.toString();
	}
	
	
	public static boolean copyFile(final File src, final File dst)
			throws IOException {
		if (src.getAbsolutePath().toString()
				.equals(dst.getAbsolutePath().toString()))
			return true;
		else {
			final InputStream is = new FileInputStream(src);
			copyFile(is, dst);
		}
		return true;
	}

	public static void copyFile(final InputStream is, final File dst)
			throws IOException {

		final OutputStream os = new FileOutputStream(dst);
		final byte[] buff = new byte[1024];
		int len;
		while ((len = is.read(buff)) > 0) {
			os.write(buff, 0, len);
		}
		is.close();
		os.close();

	}

	public static void deleteFile(File dir, String fileName) {
		File file = new File(dir, fileName);
		if (file.exists())
			file.delete();
	}

	public static void deleteFile(String path) {
		try {
			File file = new File(path);
			if (file.exists())
				file.delete();
		} catch (Exception e) {
		}
	}

	public static boolean fileIsExits(String filePath) {
		if (filePath != null) {
			File file = new File(filePath);
			if (file.exists())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	public static void deleteFolder(final File folder) {
		final File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (final File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	public static File getSDDir(Activity context) {

		return Environment.getExternalStorageDirectory();

	}

	public static void writeObject(Context context, String key, Object object) throws IOException {
		FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(object);
		oos.close();
		fos.close();
	}

	public static Object readObject(Context context, String key) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = context.openFileInput(key);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		return object;
	}


}
