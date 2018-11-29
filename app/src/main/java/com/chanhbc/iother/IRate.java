package com.chanhbc.iother;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
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

    private int upperBound = 2;
    private static final String KEY_IS_RATE = "key_is_rate";
    private boolean isRateAppTemp = false;

    public IRate(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_rate);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.context = context;
        initDialog();
    }

    private void initDialog() {
        Button btnOk = (Button) findViewById(R.id.btn_ok);
        Button btnNotNow = (Button) findViewById(R.id.btn_cancel);
        TextView txtAppName = (TextView) findViewById(R.id.txt_name_app);
        ImageView imageIcon = (ImageView) findViewById(R.id.img_icon_app);

        String appName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            Drawable icon = packageManager.getApplicationIcon(context.getPackageName());
            imageIcon.setImageDrawable(icon);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
         txtAppName.setText(appName);
        mBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) mBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#ff2d54"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#B0B0B6"), PorterDuff.Mode.SRC_ATOP);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRateAppTemp) {
                    if (mBar.getRating() > upperBound) {
                        openMarket();
                        notShowDialogWhenUserRateHigh();
                    } else {
                        notShowDialogWhenUserRateHigh();
                    }
                }
                Toast.makeText(context, "please rate 5 stars", Toast.LENGTH_SHORT).show();
            }
        });
        btnNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
        mBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                isRateAppTemp = true;
                if (ratingBar.getRating() > upperBound) {
                    dismiss();
                    openMarket();
                    notShowDialogWhenUserRateHigh();
                } else {
                    dismiss();
                    notShowDialogWhenUserRateHigh();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        } catch (Exception ignored) {

        }

    }

    public boolean isRate() {
        return IShared.getIShare(context).getBoolean(KEY_IS_RATE, false);
    }

    private void notShowDialogWhenUserRateHigh() {
        SharedPreferences shared = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(KEY_IS_RATE, true);
        editor.apply();
        ((Activity) context).finish();
    }

    private void openMarket() {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}