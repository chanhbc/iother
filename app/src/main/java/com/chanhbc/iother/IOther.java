package com.chanhbc.iother;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class IOther {
    private static IOther instance;
    private Context context;

    public IOther(Context context) {
        this.context = context;
    }

    public static IOther getIOther(Context context) {
        if (instance == null) {
            instance = new IOther(context);
        }
        return instance;
    }

    @SuppressLint("MissingPermission")
    public void runVibrate(long millisecond) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        assert v != null;
        v.vibrate(millisecond);
    }

    /**
     * MyService.class.toString().replace("class ", "")
     */

    public boolean checkService(String str) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = manager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : services) {
            if (info.service.getClassName().toUpperCase().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public void privacyPolicy(String pack) {
        Uri uri = Uri.parse(pack);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        this.startActivity(intent);
    }

    public int getColorResource(int id){
        return ResourcesCompat.getColor(context.getResources(), id, null);
    }

    public static String convertPercentToHex(float percent) {
        int hexInt = (int) (percent * 255);
        if (hexInt > 255) {
            hexInt = 255;
        }
        String hex = Integer.toHexString(hexInt);
        if (hex.length() < 2) {
            hex = "0" + Integer.toHexString(hexInt);
        }
        return hex;
    }

    public void openMarket() {
        final String appPackageName = context.getPackageName();
        try {
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException ignored) {
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public String readFileAssets(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String mLine;
            StringBuilder s = new StringBuilder();
            while ((mLine = reader.readLine()) != null) {
                if (mLine.length() > 0) {
                    s.append(mLine);
                }
            }
            reader.close();
            return s.toString();
        } catch (IOException ignored) {
        }
        return "";
    }

    private void startActivity(Intent activity) {
        try {
            context.startActivity(activity);
        } catch (ActivityNotFoundException e) {

        }
    }

    private void startActivityForResult(Context contextActivity, Intent activity, int requestCode) {
        try {
            ((Activity) contextActivity).startActivityForResult(activity, requestCode);
        } catch (ActivityNotFoundException e) {

        }
    }

    public Bitmap getBitmapApplication() {
        try {
            PackageManager packageManager = context.getPackageManager();
            Drawable icon = packageManager.getApplicationIcon(context.getPackageName());
            return drawableToBitmap(icon);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        Bitmap bitmap = null;
        if (drawable.getIntrinsicWidth() > 0 || drawable.getIntrinsicHeight() > 0) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }

        return bitmap;
    }

    public static File saveBitmap(Bitmap bmp, String folder, String name) {
        String path = Environment.getExternalStorageDirectory().toString()
                + "/" + folder + "/";
        File f = new File(path);
        if (!f.exists()) {
            if (!f.mkdirs()) {
                ILog.e("Create directory fail");
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("_HH_mm_ss_dd_MM_yyyy");
        String currentDateandTime = sdf.format(new Date());
        String fileName = name + currentDateandTime;
        File file = new File(path, fileName + ".png");
        if (file.exists()) {
            if (!file.delete()) {
                ILog.e("delete \"" + fileName + "\" error");
            }
        }
        File nomedia = new File(path, ".nomedia");
        if (!nomedia.exists()) {
            try {
                if (!nomedia.createNewFile()) {
                    ILog.e("create .nomedia fail");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            OutputStream outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static Bitmap getBitmapResize(Bitmap bitmap, int max, boolean filter) {
        int wb = bitmap.getWidth();
        int hb = bitmap.getHeight();
        int maxb = wb > hb ? wb : hb;
        float r = (float) maxb / max;
        int w = (int) (wb / r);
        int h = (int) (hb / r);
        return Bitmap.createScaledBitmap(bitmap, w, h, filter);
    }

    public void checkPermission(Context contextActivity) {
        String manufacturerXiaomi = "xiaomi";
        String manufacturerHuawei = "huawei";
        if (manufacturerXiaomi.equalsIgnoreCase(Build.MANUFACTURER)) {
            if (!IShared.getIShare(context).getBoolean(IConstant.PERMISSION_AUTO_START, false)) {
                AlertDialog alertDialog = new AlertDialog.Builder(contextActivity)
                        .setTitle("Notification")
                        .setMessage("Device Xiaomi need auto start permission, you can turn on this permission?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent();
                                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                                startActivity(intent);
                                IShared.getIShare(context).putBoolean(IConstant.PERMISSION_AUTO_START, true);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                alertDialog.show();
            }
        }
        if (manufacturerHuawei.equalsIgnoreCase(Build.MANUFACTURER)) {
            if (!IShared.getIShare(context).getBoolean(IConstant.PERMISSION_AUTO_START, false)) {
                AlertDialog alertDialog = new AlertDialog.Builder(contextActivity)
                        .setTitle("Notification")
                        .setMessage("Device Huawei need protected permission, you can turn on this permission?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent();
                                intent.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
                                startActivity(intent);
                                IShared.getIShare(context).putBoolean(IConstant.PERMISSION_AUTO_START, true);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                alertDialog.show();
            }
        }
    }

    public boolean checkDrawOverlaysPermission(final Context contextActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            AlertDialog alertDialog = new AlertDialog.Builder(contextActivity)
                    .setTitle("Permission")
                    .setMessage("Application need draw overlays permission, you can turn on this permission?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    Uri.parse("package:" + context.getPackageName()));
                            startActivityForResult(contextActivity, intent, IConstant.REQUEST_CODE_DRAW_OVERLAY_PERMISSIONS);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            alertDialog.show();
            return false;
        }
        return true;
    }

    public static String unAccent(String s) {
        // option special 'Đ-đ' :))
        s = s.replace("Đ", "D").replace("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public int spToPx(int px) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics()));
    }

    public void refreshGallery(String path) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(new File(path)));
        context.sendBroadcast(mediaScanIntent);
    }

    public static String getTimeFormat(long timeMili) {
        timeMili /= 1000; // mili second
        String tm = "";
        long s;
        long m;
        long h;
        s = timeMili % 60;
        m = (timeMili - s) / 60;
        if (m >= 60) {
            h = m / 60;
            m = m % 60;
            if (h > 0) {
                if (h < 10)
                    tm += "0" + h + ":";
                else
                    tm += h + ":";
            }
        }
        if (m < 10)
            tm += "0" + m + ":";
        else
            tm += m + ":";
        if (s < 10)
            tm += "0" + s;
        else
            tm += s + "";
        return tm;
    }

    public static String getTimeFormatMili(long timeMili) {
        int ml = (int) (timeMili % 1000);
        timeMili /= 1000; // mili second
        String tm = "";
        long s;
        long m;
        long h;
        s = timeMili % 60;
        m = (timeMili - s) / 60;
        if (m >= 60) {
            h = m / 60;
            m = m % 60;
            if (h > 0) {
                if (h < 10)
                    tm += "0" + h + ":";
                else
                    tm += h + ":";
            }
        }
        if (m < 10)
            tm += "0" + m + ":";
        else
            tm += m + ":";
        if (s < 10)
            tm += "0" + s;
        else
            tm += s + "";
        if (ml < 10) {
            return tm + ".00" + ml;
        } else if (ml < 100) {
            return tm + ".0" + ml;
        } else {
            return tm + "." + ml;
        }
    }

    public void toast(Object s) {
        toast(s, Toast.LENGTH_SHORT);
    }

    public void toast(Object s, int time) {
        Toast.makeText(context, s.toString(), time).show();
    }

    public void about() {
        String version = "";
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        toast(version);
    }

    public void feedback(String email) {
        String version = "";
        String appName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        feedback(appName, email, version);
    }

    public int getStatusBarHeight() {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public int getNavigationBarHeight() {
        boolean isNavigationBar = context.getResources().getBoolean(context.getResources().getIdentifier("config_showNavigationBar", "bool", "android"));
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0 && isNavigationBar) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }


    public void feedback(String app_name, String supportEmail, String version) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setType("text/html");
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{supportEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback App: " +
                app_name + "(" + context.getPackageName() + ", version: " + version + ")");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, "Send mail Report App !"));
    }
}
