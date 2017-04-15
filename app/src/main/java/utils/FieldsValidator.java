package utils;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.ArrayList;

public class FieldsValidator {

    public static boolean validateEditText(EditText edit, String errorMessage) {

        if (TextUtils.isEmpty(edit.getText())) {
            // EditText was empty
            // Do something fancy
            edit.requestFocus();
            edit.setError(errorMessage);
            return false;
        }
        return true;
    }

    public static boolean validateSomeEditText(ArrayList<EditText> editTextList, String errorMessage) {

        for (EditText edit : editTextList) {
            if (TextUtils.isEmpty(edit.getText())) {
                // EditText was empty
                // Do something fancy
                edit.requestFocus();
                edit.setError(errorMessage);
                return false;
            }
        }
        return true;
    }

    public static boolean validateEmailEditText(EditText edit, String errorMessage) {

        if (TextUtils.isEmpty(edit.getText())) {
            // EditText was empty
            // Do something fancy
            edit.requestFocus();
            edit.setError(errorMessage);
            return false;
        } else if (!isValidEmail(edit.getText().toString())) {
            edit.requestFocus();
            edit.setError(errorMessage);
            return false;
        }
        return true;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
