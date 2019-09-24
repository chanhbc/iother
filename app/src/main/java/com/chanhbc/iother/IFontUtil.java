package com.chanhbc.iother;

import android.content.Context;
import android.graphics.Typeface;

public class IFontUtil {
    public static Typeface getTypeface(Context context, String name) {
        try {
            return Typeface.createFromAsset(context.getAssets(), name);
        }catch (Exception e){
            return null;
        }
    }

    public static Typeface getTypeface(Context context, String name, int format) {
        return getTypeface(context, IConstant.FONTS_FOLDER + IConstant.SLASH + name +
                IConstant.PERIOD + (format == 1 ? IConstant.TTF : IConstant.OTF));
    }

    public static Typeface getTypeface(Context context, int fontDefault) {
        final String fontName;
        switch (fontDefault) {
            case 1:
                fontName = IConstant.FONT_DEFAULT_BOLD;
                break;

            case 2:
                fontName = IConstant.FONT_DEFAULT_SEMI_BOLD;
                break;

            case 3:
                fontName = IConstant.FONT_DEFAULT_MEDIUM;
                break;

            case 4:
                fontName = IConstant.FONT_DEFAULT_REGULAR;
                break;

            case 5:
                fontName = IConstant.FONT_DEFAULT_LIGHT;
                break;

            default:
                fontName = IConstant.FONT_DEFAULT_MEDIUM;
                break;
        }
        return getTypeface(context, IConstant.FONTS_FOLDER + IConstant.SLASH + fontName);
    }
}
