package com.chanhbc.iother;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class ITextView extends AppCompatTextView {
	public ITextView(Context context) {
		this(context, null);
	}

	public ITextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
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
			TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ITextView);
			String fontName = attributes.getString(R.styleable.ITextView_font_name);
			int format = attributes.getInt(R.styleable.ITextView_font_format, 1);
			if (fontName == null || fontName.isEmpty()) {
				return;
			}
			setTypeface(IFontUtil.getTypeface(fontName, format));
		} catch (Exception e) {
			ILog.e(e);
		}
	}
}
