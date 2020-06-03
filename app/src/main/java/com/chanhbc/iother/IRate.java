package com.chanhbc.iother;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class IRate extends Dialog {
    private Context context;
    private RatingBar mBar;
    private String email;
    private int numberStar = 3;
    private boolean isExit;
    private Button btnNotNow;

    public void setNumberStar(int numberStar) {
        this.numberStar = numberStar;
    }

    public IRate(Context context, String email) {
        this(context, email, 3);
    }

    public IRate(Context context, String email, int times) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_rate);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.context = context;
        this.email = email;
        initDialog();
        if (times > 0) {
            initShowWhenAppOpen(times);
        }
    }

    private static final String KEY_COUNT_OPEN_APP = "key_count_open_app";
    private static final int VALUE_COUNT_OPEN_APP_DEFAULT = 0;

    private void initShowWhenAppOpen(int times) {
        if (!isRate()) {
            int countOpen = IShared.getInstance(context).getInt(KEY_COUNT_OPEN_APP, VALUE_COUNT_OPEN_APP_DEFAULT);
            countOpen++;
            if (countOpen >= times) {
                countOpen = times;
                show();
            }
            IShared.getInstance(context).putInt(KEY_COUNT_OPEN_APP, countOpen);
        }
    }

    private void initDialog() {
        final Button btnOk = findViewById(R.id.btn_ok);
        btnNotNow = findViewById(R.id.btn_cancel);
        TextView txtAppName = findViewById(R.id.txt_name_app);
        ImageView imageIcon = findViewById(R.id.img_icon_app);

        Drawable drawable = IOther.getInstance(context).getApplicationDrawable();
        if (drawable != null) {
            imageIcon.setImageDrawable(drawable);
        }
        txtAppName.setText(IOther.getInstance(context).getAppName());
        mBar = findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) mBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#ff2d54"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#B0B0B6"), PorterDuff.Mode.SRC_ATOP);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBar.getRating() == 0) {
                    Toast.makeText(context, context.getString(R.string.plz_rate_5_star), Toast.LENGTH_SHORT).show();
                } else {
                    if (mBar.getRating() > numberStar) {
                        IShared.getInstance(context).putBoolean(IConstant.KEY_IS_RATE, true);
                        IOther.getInstance(context).openMarket();
                    } else {
                        IOther.getInstance(context).feedback(email);
                    }
                    dismiss();
                }
            }
        });
        btnNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (isExit) {
                    finish();
                }
            }
        });
        mBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                btnOk.setText(ratingBar.getRating() > numberStar ?
                        context.getString(R.string.rate) : context.getString(R.string.feedback));
            }
        });
        setRateNumber(5);
    }

    private void setRateNumber(int rateNumber) {
        mBar.setNumStars(rateNumber);
    }

    private void finish() {
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public void show(boolean exit) {
        this.isExit = exit;
        btnNotNow.setText(isExit ? R.string.exit : R.string.cancel);
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Window window = getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        } catch (Exception ignored) {

        }
    }

    public static boolean isRate(Context context) {
        return IShared.getInstance(context).getBoolean(IConstant.KEY_IS_RATE, false);
    }

    public boolean isRate() {
        return isRate(context);
    }
}