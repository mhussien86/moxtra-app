package utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class LocaleUtility
{

	/**
	 * change the language of the application according to the user need
	 * 
	 * @param lang
	 */
	public static void setUpApplicationLocale(Context context, String lang)
	{
		Locale locale = new Locale(lang);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		context.getResources()
				.updateConfiguration(config, context.getResources().getDisplayMetrics());

	}

	public static String getSystemLocale()
	{
		return Locale.getDefault().getLanguage();
	}

	public static String getApplicationLocale(Context context)
	{
		return context.getResources().getConfiguration().locale.getLanguage();
	}
}
