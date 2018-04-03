package kunlunengine.com.datadisplayplatform.agentwebview.agentweb;

import android.webkit.WebView;

/**
 * Created by cenxiaozhong.
 * source code  https://github.com/Justson/AgentWeb
 */

public interface IndicatorController {

    void progress(WebView v, int newProgress);

    BaseIndicatorSpec offerIndicator();

    void showProgressBar();

    void setProgressBar(int newProgress);

    void finish();
}
