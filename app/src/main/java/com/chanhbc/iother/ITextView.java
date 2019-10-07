package com.chanhbc.iother;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class ITextView extends AppCompatTextView {
	public ITextView(Context context) {
		this(context, null);
	}

	public ITextView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.textViewStyle);
	}

	public ITextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	@SuppressLint ("Recycle")
	private void init(Context context, AttributeSet attrs) {
		if (attrs == null) {
			return;
		}
		try {
			final int textStyle = attrs.getAttributeIntValue(IConstant.ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
            final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ITextView);
            final String fontName = attributes.getString(R.styleable.ITextView_itv_font_name);
            final Typeface typeface;
            if (fontName != null && !fontName.isEmpty()) {
                final int format = attributes.getInt(R.styleable.ITextView_itv_font_format, -1);
                typeface = IFontUtil.getTypeface(context, fontName, format);
            }else {
				final int fontDefault = attributes.getInt(R.styleable.ITextView_itv_font_default, -1);
				typeface = IFontUtil.getTypeface(context, fontDefault);
			}
            if (typeface != null) {
                setTypeface(typeface, textStyle);
            }
		} catch (Exception e) {
			ILog.e(e);
		}
	}
}
