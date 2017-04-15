package utils;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author islam farid
 */
public class PhoneUtility {

    public static int REQUEST_PHONE = 1;

    public static void makeARegularCall(Activity context, String phoneNumber) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE);
            return;
        }else {
            String uri = "tel:" + Uri.encode(phoneNumber.trim());
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(uri));
            context.startActivity(intent);
        }

    }

    public static void OpenClientMail(Context context, String emailAddress,String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        List<ResolveInfo> resInfo = context.getPackageManager()
                .queryIntentActivities(intent, 0);

        if (!resInfo.isEmpty()) {
            for (ResolveInfo info : resInfo) {
                if (info.activityInfo.packageName.toLowerCase().contains(
                        "email")
                        || info.activityInfo.name.toLowerCase().contains(
                        "email")) {
                    intent.putExtra(Intent.EXTRA_EMAIL,
                            new String[]{emailAddress});
                    if(subject != null && !subject.equals(""))
                    { intent.putExtra(Intent.EXTRA_SUBJECT, subject);}
                    // intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
                    intent.setPackage(info.activityInfo.packageName);
                    context.startActivity(Intent.createChooser(intent,
                            "Send email"));
                }
            }
        }
    }

    public static void sendSms(Context context, String phone, String text) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("sms:" + phone));
        intent.putExtra("sms_body", text);
        context.startActivity(intent);
    }

    public static void addToContacts(Context context, String name, String number) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        int rawContactID = ops.size();

        // Adding insert operation to operations list
        // to insert a new raw contact in the table ContactsContract.RawContacts
        ops.add(ContentProviderOperation
                .newInsert(RawContacts.CONTENT_URI)
                .withValue(RawContacts.ACCOUNT_TYPE, null)
                .withValue(RawContacts.ACCOUNT_NAME, null).build());

        // Adding insert operation to operations list
        // to insert display name in the table ContactsContract.Data
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                        rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE,
                        StructuredName.CONTENT_ITEM_TYPE)
                .withValue(StructuredName.DISPLAY_NAME, name).build());

        // Adding insert operation to operations list
        // to insert Mobile Number in the table ContactsContract.Data
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                        rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE,
                        Phone.CONTENT_ITEM_TYPE)
                .withValue(Phone.NUMBER, number)
                .withValue(Phone.TYPE, Phone.TYPE_MOBILE)
                .build());

        // Adding insert operation to operations list
        // to insert Home Phone Number in the table ContactsContract.Data
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                        rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE,
                        Phone.CONTENT_ITEM_TYPE).withValue(Phone.NUMBER, null)
                .withValue(Phone.TYPE, Phone.TYPE_HOME).build());

        // Adding insert operation to operations list
        // to insert Home Email in the table ContactsContract.Data
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                        rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE,
                        Email.CONTENT_ITEM_TYPE).withValue(Email.ADDRESS, null)
                .withValue(Email.TYPE, Email.TYPE_HOME).build());

        // Adding insert operation to operations list
        // to insert Work Email in the table ContactsContract.Data
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                        rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE,
                        Email.CONTENT_ITEM_TYPE).withValue(Email.ADDRESS, null)
                .withValue(Email.TYPE, Email.TYPE_WORK).build());

        try {
            // Executing all the insert operations as a single database
            // transaction
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY,
                    ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }
    public static void startBrowser(Context context, String url)
    {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
        {
            url = "http://" + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }


    public static void openMailClient(Context context , String mailAddress, String subject){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("vnd.android.cursor.item/email");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {mailAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "My email content");
        context.startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
    }


    public static void openGoogleMaps(Context context , String location, String name){
        Uri gmmIntentUri = Uri.parse("geo:"+location+"?q="+location+"("+name+")");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }
    }

    public static void watchYoutubeVideo(Context context ,String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

}
