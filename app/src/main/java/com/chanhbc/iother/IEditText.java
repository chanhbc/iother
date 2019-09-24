package com.chanhbc.iother;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class IEditText extends AppCompatEditText {
    public IEditText(Context context) {
        this(context, null);
    }

    public IEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressLint("Recycle")
    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        try {
            final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.IEditText);
            final String fontName = attributes.getString(R.styleable.IEditText_iet_font_name);
            Typeface typeface;
            if (fontName != null && !fontName.isEmpty()) {
                final int format = attributes.getInt(R.styleable.IEditText_iet_font_format, -1);
                typeface = IFontUtil.getTypeface(context, fontName, format);
                if (typeface != null) {
                    setTypeface(typeface);
                    return;
                }
            }
            final int fontDefault = attributes.getInt(R.styleable.IEditText_iet_font_default, -1);
            typeface = IFontUtil.getTypeface(context, fontDefault);
            if (typeface != null) {
                setTypeface(typeface);
                return;
            }
            setTypeface(Typeface.DEFAULT);
        } catch (Exception e) {
            ILog.e(e);
        }
    }
}
