package kunlunengine.com.datadisplayplatform.agentwebview.agentweb;

import android.view.KeyEvent;

/**
 * Created by cenxiaozhong .
 * source code  https://github.com/Justson/AgentWeb
 */

public interface IEventHandler {

    boolean onKeyDown(int keyCode, KeyEvent event);


    boolean back();
}
