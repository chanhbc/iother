package com.chanhbc.iother;

import android.content.Context;
import android.graphics.Typeface;

public class IFontUtil {
    public static Typeface getTypeface(Context context, String name) {
        return Typeface.createFromAsset(context.getAssets(), name);
    }

    public static Typeface getTypeface(String name, int format) {
        return Typeface.createFromAsset(IApp.getContext().getAssets(),
                IConstant.FONT_FOLDER + IConstant.FLASH + name + IConstant.DOT +
                        (format == 1 ? IConstant.TTF : IConstant.OTF));
    }
}
