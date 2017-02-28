package me.kiuber.bc.util;

import android.content.Context;

/**
 * Created by kiuber on 2017/2/28 0028.
 */

public class AppUtil {
    public static String isLogin(Context context) {
        String nickname = SharedPreferenceUtil.getOne(context, "app_config", "nickname");
        if (nickname != null && !nickname.equals("")) {
            return nickname;
        } else {
            return null;
        }
    }
}
