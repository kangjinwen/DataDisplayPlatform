package kunlunengine.com.datadisplayplatform.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import kunlunengine.com.datadisplayplatform.agentwebview.agentweb.AgentWeb;
import kunlunengine.com.datadisplayplatform.xframe.widget.XToast;

/**
 * Created by cenxiaozhong on 2017/5/14.
 *  source CODE  https://github.com/Justson/AgentWeb
 */

public class AndroidInterface {

    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Context context;

    public AndroidInterface(AgentWeb agent, Context context) {
        this.agent = agent;
        this.context = context;
    }



    @JavascriptInterface
    public void callAndroid(final String msg) {
        deliver.post(new Runnable() {
            @Override
            public void run() {

                Log.i("Info", "main Thread:" + Thread.currentThread());
                Toast.makeText(context.getApplicationContext(), "" + msg, Toast.LENGTH_LONG).show();
            }
        });
        Log.i("Info", "Thread:" + Thread.currentThread());
    }

    @JavascriptInterface
    public void showSuccess(){
        XToast.success("提交成功",6);
    }

//    @JavascriptInterface
//    public String getLocation() {
//        String loc="";
//        App.mLocationClient.start();
//        if (XEmptyUtils.isEmpty(App.latitude) || XEmptyUtils.isEmpty(App.longitude) || App.latitude == 0.0 || App.longitude == 0.0) {
//            App.mLocationClient.restart();
//            return loc="";
//        }
//        String lat = App.latitude + "";
//        String longi = App.longitude + "";
//         loc = lat + "," + longi;
//        App.mLocationClient.stop();
//        XToast.info(loc);
//        return loc;
//    }
//    @JavascriptInterface
//    public void showToast(String msg){
//        Toast.makeText(context.getApplicationContext(), "" + msg, Toast.LENGTH_LONG).show();
//        MediaRecorderConfig config = new MediaRecorderConfig.Buidler()
//                .fullScreen(false)
//                .smallVideoWidth(320)
//                .smallVideoHeight(480)
//                .recordTimeMax(60000)
//                .recordTimeMin(2000)
//                .maxFrameRate(20)
//                .videoBitrate(580000)
//                .captureThumbnailsTime(1)
//                .build();
//        MediaRecorderActivity.goSmallVideoRecorder((Activity) context, MainActivity.class.getName(), config);
//    }

}
