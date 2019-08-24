package com.chanhbc.iother;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint ("AppCompatCustomView")
public class IButton extends Button {
	public IButton(Context context) {
		this(context, null);
	}

	public IButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public IButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	@SuppressLint ("Recycle")
	private void init(Context context, AttributeSet attrs) {
		if (attrs == null) {
			return;
		}
		try {
			TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.IButton);
			String fontName = attributes.getString(R.styleable.IButton_font_name);
			int format = attributes.getInt(R.styleable.IButton_font_format, 1);
			if (fontName == null || fontName.isEmpty()) {
				return;
			}
			setTypeface(IFontUtil.getTypeface(fontName, format));
		} catch (Exception e) {
			ILog.e(e);
		}
	}
}
