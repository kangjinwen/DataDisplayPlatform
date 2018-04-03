package kunlunengine.com.datadisplayplatform;


import cn.jpush.android.api.JPushInterface;
import kunlunengine.com.datadisplayplatform.keepalive.DaemonEnv;
import kunlunengine.com.datadisplayplatform.keepalive.TraceServiceImpl;
import kunlunengine.com.datadisplayplatform.xframe.XFrame;
import kunlunengine.com.datadisplayplatform.xframe.base.XApplication;

public class App extends XApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化日志
        XFrame.initXLog();
        JPushInterface.init(getApplicationContext());
        //需要在 Application 的 onCreate() 中调用一次 DaemonEnv.initialize()
        DaemonEnv.initialize(this, TraceServiceImpl.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
        TraceServiceImpl.sShouldStopService = false;
        DaemonEnv.startServiceMayBind(TraceServiceImpl.class);
    }
}
