package kunlunengine.com.datadisplayplatform.agentwebview.agentweb;

import android.app.Activity;
import android.os.Handler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;

/**
 * Created by cenxiaozhong on 2017/12/6.
 *
 */
public  class AgentWebUIControllerImplBase extends AgentWebUIController {


    public static AgentWebUIController build() {
        return new AgentWebUIControllerImplBase();
    }

    @Override
    public void onJsAlert(WebView view, String url, String message) {
        getDelegate().onJsAlert(view, url, message);
    }

    @Override
    public void onAskOpenPage(WebView view, String url, String message, String confirm, String title, Handler.Callback callback) {
        getDelegate().onAskOpenPage(view,url,message,confirm,title,callback);
    }

    @Override
    public void onJsConfirm(WebView view, String url, String message, JsResult jsResult) {
        getDelegate().onJsConfirm(view, url, message, jsResult);
    }

    @Override
    public void showChooser(WebView view, String url, String[] ways, Handler.Callback callback) {
        getDelegate().showChooser(view, url, ways, callback);
    }

    @Override
    public void onForceDownloadAlert(String url, DefaultMsgConfig.DownloadMsgConfig message, Handler.Callback callback) {
        getDelegate().onForceDownloadAlert(url, message, callback);
    }

    @Override
    public void onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult jsPromptResult) {
        getDelegate().onJsPrompt(view, url, message, defaultValue, jsPromptResult);
    }

    @Override
    public void onMainFrameError(WebView view, int errorCode, String description, String failingUrl) {
        getDelegate().onMainFrameError(view,errorCode,description,failingUrl);
    }

    @Override
    public void onShowMainFrame() {
        getDelegate().onShowMainFrame();
    }

    @Override
    public void onLoading(String msg) {
        getDelegate().onLoading(msg);
    }

    @Override
    public void cancelLoading() {
        getDelegate().cancelLoading();
    }


    @Override
    public void showMessage(String message, String from) {
        getDelegate().showMessage(message, from);
    }

    @Override
    protected void bindSupportWebParent(WebParentLayout webParentLayout, Activity activity) {
        getDelegate().bindSupportWebParent(webParentLayout, activity);
    }


}
