package kunlunengine.com.datadisplayplatform.agentwebview.agentweb;

/**
 * source code  https://github.com/Justson/AgentWeb
 */

public class HookManager {



    public static AgentWeb hookAgentWeb(AgentWeb agentWeb, AgentWeb.AgentBuilder agentBuilder) {
        return agentWeb;
    }

    public static boolean permissionHook(String url, String[]permissions){
        return true;
    }




}
