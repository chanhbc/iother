package com.chanhbc.iother;

import android.content.Context;
import android.graphics.Typeface;

public class IFontUtil {
    public static Typeface getTypeface(Context context, String name) {
        return Typeface.createFromAsset(context.getAssets(), name);
    }

    public static Typeface getTypeface(Context context, String name, int format) {
        return Typeface.createFromAsset(context.getAssets(),
                IConstant.FONTS_FOLDER + IConstant.SLASH + name + IConstant.PERIOD +
                        (format == 1 ? IConstant.TTF : IConstant.OTF));
    }
}
