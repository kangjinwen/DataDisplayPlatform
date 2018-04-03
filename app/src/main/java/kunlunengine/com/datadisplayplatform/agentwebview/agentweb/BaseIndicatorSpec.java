package kunlunengine.com.datadisplayplatform.agentwebview.agentweb;

/**
 * source code  https://github.com/Justson/AgentWeb
 */

public interface BaseIndicatorSpec {
    /**
     * indicator
     */
    void show();

    void hide();

    void reset();

    void setProgress(int newProgress);

}
