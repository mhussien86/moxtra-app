package utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

//	private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    /**
     * Usable for webview inner mime-type
     */
    public static final String ENCODING_UTF8_WEBVIEW = "text/html; charset=UTF-8";
    public static final String ENCODING_UTF8 = "UTF-8";
    private StringUtils() { }

    /**
     * Returns a md5 hash of the passed string
     *
     * @param s
     *            The string to be hashed
     * @return The hashed string
     */
    public static String md5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * Returns a sha1 hash of the passed string
     *
     * @param value
     *            the string that should be hashed
     * @return the hashed string
     */

    public static String sha1(String value) {
        String sha1 = "";
        Formatter formatter = new Formatter();
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
//            crypt.update(value.getBytes(HTTP));

            for (byte b : crypt.digest()) {
                formatter.format("%02x", b);
            }
            sha1 = formatter.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            formatter.close();
        }
        return sha1;
    }

    public static String getAsset(Context context, String fileName) {
        try {
            InputStream is = context.getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean isEmpty(String value) {
        return (value == null) || value.trim().equals("");
    }

    public static boolean isValidEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidMsisdn(String msisdn) {
        if (msisdn != null && !msisdn.equals("") && msisdn.length() > 6) {
            Pattern p = Pattern.compile("^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$");
            Matcher m = p.matcher(msisdn);
            return m.matches();
        } else {
            return false;
        }
    }

    public static String cleanMsisdn(long msisdn) {
        return cleanMsisdn(String.valueOf(msisdn));
    }

    public static String cleanMsisdn(String msisdn) {
        if (msisdn != null) {
            if (msisdn.startsWith("49")) {
                msisdn = "0" + msisdn.substring(2);
            }
            return msisdn.replace("(", "").replace(")", "").replace(" ", "").replace("+", "");
        } else {
            return null;
        }
    }

    public static String formatDate(String returnFormat, String unformattedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(returnFormat, Locale.GERMAN);
        return sdf.format(unformattedDate);
    }

    public static String formatDate(String returnFormat, String dateFormat, String unformattedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);
        try {
            Date d = sdf.parse(unformattedDate);
            sdf = new SimpleDateFormat(returnFormat);
            return sdf.format(d);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatTimestamp(String returnFormat, String timestamp) {
        Date date = new Date();
        long time = Long.parseLong(timestamp);
        if (timestamp.length() == 8) {
            time = time * 1000L;
        } else if (timestamp.length() == 10) {
            time = time * 1000L;
        }
        date.setTime(time);
        SimpleDateFormat sdf = new SimpleDateFormat(returnFormat);
        return sdf.format(date);
    }

    public static String escapeHtml(String string) {
        StringBuffer sb = new StringBuffer(string.length());
        // true if last char was blank
        boolean lastWasBlankChar = false;
        int len = string.length();
        char c;

        for (int i = 0; i < len; i++) {
            c = string.charAt(i);
            if (c == ' ') {
                // blank gets extra work,
                // this solves the problem you get if you replace all
                // blanks with &nbsp;, if you do that you loss
                // word breaking
                if (lastWasBlankChar) {
                    lastWasBlankChar = false;
                    sb.append("&nbsp;");
                } else {
                    lastWasBlankChar = true;
                    sb.append(' ');
                }
            } else {
                lastWasBlankChar = false;
                //
                // HTML Special Chars
                if (c == '"')
                    sb.append("&quot;");
                else if (c == '&')
                    sb.append("&amp;");
                else if (c == '<')
                    sb.append("&lt;");
                else if (c == '>')
                    sb.append("&gt;");
                else if (c == '\n')
                    // Handle Newline
                    sb.append("&lt;br/&gt;");
                else {
                    int ci = 0xffff & c;
                    if (ci < 160)
                        // nothing special only 7 Bit
                        sb.append(c);
                    else {
                        // Not 7 Bit use the unicode system
                        sb.append("&#");
                        sb.append(new Integer(ci).toString());
                        sb.append(';');
                    }
                }
            }
        }
        return sb.toString();
    }

    public static String toUnicode(String str) {
        StringBuffer ostr = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ((ch >= 0x0020) && (ch <= 0x007e)) {
                ostr.append(ch);
            } else {
                ostr.append("\\U");
                String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);
                for (int j = 0; j < 4 - hex.length(); j++)
                    ostr.append("0");
                ostr.append(hex.toLowerCase());
            }
        }
        return (new String(ostr));
    }

    public static String encodeLastStringInURL(String url)
            throws UnsupportedEncodingException, URISyntaxException {
        int pos = url.lastIndexOf('/') + 1;

        String finalURL = url.substring(0, pos)
                + URLEncoder.encode(
                (url.substring(pos)),
                "utf-8");
        return finalURL;
    }
    public static HashMap<String,String> parseStringArray(Context context,int stringArrayResourceId) {
        String[] stringArray =context.getResources().getStringArray(stringArrayResourceId);
        HashMap<String,String> outputArray = new HashMap<String,String>(stringArray.length);
        for (String entry : stringArray) {
            String[] splitResult = entry.split("=", 2);
            outputArray.put(splitResult[1], splitResult[0]);
        }
        return outputArray;
    }

    public static String convertStreamToString(InputStream is)
    {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
