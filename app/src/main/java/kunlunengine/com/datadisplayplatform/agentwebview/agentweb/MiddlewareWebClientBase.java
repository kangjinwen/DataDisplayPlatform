package kunlunengine.com.datadisplayplatform.agentwebview.agentweb;

import android.webkit.WebViewClient;

/**
 * Created by cenxiaozhong on 2017/12/15.
 *  https://github.com/Justson/AgentWeb
 */

public class MiddlewareWebClientBase extends WrapperWebViewClient {
    private MiddlewareWebClientBase mMiddleWrareWebClientBase;
    private String TAG = this.getClass().getSimpleName();

     MiddlewareWebClientBase(MiddlewareWebClientBase client) {
        super(client);
        this.mMiddleWrareWebClientBase = client;
    }

    MiddlewareWebClientBase(WebViewClient client) {
        super(client);
    }
    public MiddlewareWebClientBase(){
         super(null);
    }

    MiddlewareWebClientBase next() {
        LogUtils.i(TAG, "next");
        return this.mMiddleWrareWebClientBase;
    }


    @Override
     final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);

    }
     MiddlewareWebClientBase enq(MiddlewareWebClientBase middleWrareWebClientBase){
        setWebViewClient(middleWrareWebClientBase);
        this.mMiddleWrareWebClientBase = middleWrareWebClientBase;
        return middleWrareWebClientBase;
    }


}
