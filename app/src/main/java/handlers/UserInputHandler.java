package handlers;

import android.text.TextUtils;

/**
 * Created by Xaz0g on 2017-05-15.
 */

public class UserInputHandler
{
    public static boolean checkNotEmpty(String text)
    {
        return text != null && !text.isEmpty();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}