package kunlunengine.com.datadisplayplatform.xframe;


import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;

import kunlunengine.com.datadisplayplatform.xframe.utils.XDensityUtils;
import kunlunengine.com.datadisplayplatform.xframe.utils.XOutdatedUtils;
import kunlunengine.com.datadisplayplatform.xframe.utils.http.IHttpEngine;
import kunlunengine.com.datadisplayplatform.xframe.utils.http.XHttp;
import kunlunengine.com.datadisplayplatform.xframe.utils.imageload.ImageLoader;
import kunlunengine.com.datadisplayplatform.xframe.utils.imageload.XImage;
import kunlunengine.com.datadisplayplatform.xframe.utils.log.XLog;
import kunlunengine.com.datadisplayplatform.xframe.utils.log.XLogConfig;
import kunlunengine.com.datadisplayplatform.xframe.widget.loadingview.XLoadingView;
import kunlunengine.com.datadisplayplatform.xframe.widget.loadingview.XLoadingViewConfig;

public class XFrame {
    private static Context context;
    public static int screenHeight;
    public static int screenWidth;

    // #log
    public static String tag = "XFrame";
    public static boolean isDebug = true;


    public static void init(Context context) {
        XFrame.context = context;
        screenHeight = XDensityUtils.getScreenHeight();
        screenWidth = XDensityUtils.getScreenWidth();
    }

    public static XLogConfig initXLog() {
        return XLog.init();
    }

    public static XLoadingViewConfig initXLoadingView() {
        return XLoadingView.init();
    }

    public static void initXHttp(IHttpEngine httpEngine) {
        XHttp.init(httpEngine);
    }

    public static void initXImageLoader(ImageLoader loader) {
        XImage.init(loader);
    }

    public static Context getContext() {
        synchronized (XFrame.class) {
            if (XFrame.context == null)
                throw new NullPointerException("Call XFrame.init(context) within your Application onCreate() method." +
                        "Or extends XApplication");
            return XFrame.context.getApplicationContext();
        }
    }

    public static Resources getResources() {
        return XFrame.getContext().getResources();
    }

    public static String getString(@StringRes int id) {
        return getResources().getString(id);
    }

    public static Resources.Theme getTheme() {
        return XFrame.getContext().getTheme();
    }

    public static AssetManager getAssets() {
        return XFrame.getContext().getAssets();
    }

    public static Drawable getDrawable( @DrawableRes int id) {
        return XOutdatedUtils.getDrawable(id);
    }

    public static int getColor( @ColorRes int id) {
        return XOutdatedUtils.getColor(id);
    }

    public static Object getSystemService(String name){
        return XFrame.getContext().getSystemService(name);
    }

    public static Configuration getConfiguration() {
        return XFrame.getResources().getConfiguration();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return XFrame.getResources().getDisplayMetrics();
    }

}
