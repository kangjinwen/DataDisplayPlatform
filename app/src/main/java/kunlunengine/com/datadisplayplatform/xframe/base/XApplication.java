package kunlunengine.com.datadisplayplatform.xframe.base;

import android.app.Application;

import kunlunengine.com.datadisplayplatform.xframe.XFrame;


public class XApplication extends Application {
    private static XApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        XFrame.init(this);
    }


    public static XApplication getInstance() {
        return instance;
    }


}
