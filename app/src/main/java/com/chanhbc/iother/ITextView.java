package com.chanhbc.iother;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class ITextView extends TextView {
    public ITextView(Context context) {
        super(context);
        init(context, null);
    }

    public ITextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ITextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressLint("Recycle")
    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        try {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TextViewCustom);
            String fontName = attributes.getString(R.styleable.TextViewCustom_font_name);
            int format = attributes.getInt(R.styleable.TextViewCustom_font_format, 1);
            if (fontName == null) {
                return;
            }
            if (fontName.length() == 0) {
                return;
            }
            setTypeface(IFontUtil.getTypeface(fontName, format));
        } catch (Exception ignored) {

        }
    }
}
