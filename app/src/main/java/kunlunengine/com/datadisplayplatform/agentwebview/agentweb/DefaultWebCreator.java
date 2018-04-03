package kunlunengine.com.datadisplayplatform.agentwebview.agentweb;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.FrameLayout;

import kunlunengine.com.datadisplayplatform.R;

/**
 *  Created by cenxiaozhong .
 * source code  https://github.com/Justson/AgentWeb
 */

public class DefaultWebCreator implements WebCreator {

    private Activity mActivity;
    private ViewGroup mViewGroup;
    private boolean isNeedDefaultProgress;
    private int index;
    private BaseIndicatorView progressView;
    private ViewGroup.LayoutParams mLayoutParams = null;
    private int color = -1;
    private int height_dp;
    private boolean isCreated=false;
    private IWebLayout mIWebLayout;
    private BaseIndicatorSpec mBaseIndicatorSpec;
    private WebView mWebView = null;
    private FrameLayout mFrameLayout = null;
    private View targetProgress;
    private static final String TAG=DefaultWebCreator.class.getSimpleName();


    protected DefaultWebCreator(@NonNull Activity activity, @Nullable ViewGroup viewGroup, ViewGroup.LayoutParams lp, int index, int color, int height_dp, WebView webView, IWebLayout webLayout) {
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        this.isNeedDefaultProgress = true;
        this.index = index;
        this.color = color;
        this.mLayoutParams = lp;
        this.height_dp = height_dp;
        this.mWebView = webView;
        this.mIWebLayout=webLayout;
    }

   protected DefaultWebCreator(@NonNull Activity activity, @Nullable ViewGroup viewGroup, ViewGroup.LayoutParams lp, int index, @Nullable WebView webView, IWebLayout webLayout) {
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        this.isNeedDefaultProgress = false;
        this.index = index;
        this.mLayoutParams = lp;
        this.mWebView = webView;
        this.mIWebLayout=webLayout;
    }

   protected DefaultWebCreator(@NonNull Activity activity, @Nullable ViewGroup viewGroup, ViewGroup.LayoutParams lp, int index, BaseIndicatorView progressView, WebView webView, IWebLayout webLayout) {
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        this.isNeedDefaultProgress = false;
        this.index = index;
        this.mLayoutParams = lp;
        this.progressView = progressView;
        this.mWebView = webView;
        this.mIWebLayout=webLayout;
    }




    public void setWebView(WebView webView) {
        mWebView = webView;
    }

    public FrameLayout getFrameLayout() {
        return mFrameLayout;
    }


    public View getTargetProgress() {
        return targetProgress;
    }

    public void setTargetProgress(View targetProgress) {
        this.targetProgress = targetProgress;
    }

    @Override
    public DefaultWebCreator create() {


        if(isCreated){
            return this;
        }
        isCreated=true;
        ViewGroup mViewGroup = this.mViewGroup;
        if (mViewGroup == null) {
            mViewGroup = this.mFrameLayout= (FrameLayout) createWebParentLayoutWithWeb();
            mActivity.setContentView(mViewGroup);
        } else {
            if (index == -1)
                mViewGroup.addView( this.mFrameLayout= (FrameLayout) createWebParentLayoutWithWeb(), mLayoutParams);
            else
                mViewGroup.addView(this.mFrameLayout= (FrameLayout) createWebParentLayoutWithWeb(), index, mLayoutParams);
        }
        return this;
    }

    @Override
    public WebView getWebView() {
        return mWebView;
    }

    @Override
    public FrameLayout getWebParentLayout() {
        return mFrameLayout;
    }



    private ViewGroup createWebParentLayoutWithWeb() {
        Activity mActivity = this.mActivity;
        WebParentLayout mFrameLayout = new WebParentLayout(mActivity);
        mFrameLayout.setId(R.id.web_parent_layout_id);
        mFrameLayout.setBackgroundColor(Color.WHITE);
        View target=mIWebLayout==null?(this.mWebView= (WebView) web()):webLayout();
        FrameLayout.LayoutParams mLayoutParams = new FrameLayout.LayoutParams(-1, -1);
        mFrameLayout.addView(target, mLayoutParams);
        mFrameLayout.bindWebView(this.mWebView);
        LogUtils.i(TAG, "  instanceof  AgentWebView:" + (this.mWebView instanceof AgentWebView));
        if(this.mWebView instanceof AgentWebView){
            AgentWebConfig.WEBVIEW_TYPE = AgentWebConfig.WEBVIEW_AGENTWEB_SAFE_TYPE;
        }
        ViewStub mViewStub=new ViewStub(mActivity);
        mViewStub.setId(R.id.mainframe_error_viewsub_id);
        mFrameLayout.addView(mViewStub,new FrameLayout.LayoutParams(-1,-1));
        if (isNeedDefaultProgress) {
            FrameLayout.LayoutParams lp = null;
            WebIndicator mWebIndicator = new WebIndicator(mActivity);
            if (height_dp > 0)
                lp = new FrameLayout.LayoutParams(-2, AgentWebUtils.dp2px(mActivity, height_dp));
            else
                lp = mWebIndicator.offerLayoutParams();
            if (color != -1)
                mWebIndicator.setColor(color);
            lp.gravity = Gravity.TOP;
            mFrameLayout.addView((View) (this.mBaseIndicatorSpec = mWebIndicator), lp);
            mWebIndicator.setVisibility(View.GONE);
        } else if (!isNeedDefaultProgress && progressView != null) {
            mFrameLayout.addView((View) (this.mBaseIndicatorSpec = (BaseIndicatorSpec) progressView), progressView.offerLayoutParams());
            progressView.setVisibility(View.GONE);
        }
        return mFrameLayout;

    }



    private View webLayout(){
        WebView mWebView = null;
        if((mWebView=mIWebLayout.getWeb())==null){
            mWebView=web();
            mIWebLayout.getLayout().addView(mWebView,-1,-1);
            LogUtils.i(TAG,"add webview");

        }else{
            AgentWebConfig.WEBVIEW_TYPE=AgentWebConfig.WEBVIEW_CUSTOM_TYPE;
        }
        this.mWebView=mWebView;
        return mIWebLayout.getLayout();

    }

    private WebView web(){

        WebView mWebView = null;
        if (this.mWebView != null) {
            mWebView = this.mWebView;
            AgentWebConfig.WEBVIEW_TYPE = AgentWebConfig.WEBVIEW_CUSTOM_TYPE;
        } else if (AgentWebConfig.IS_KITKAT_OR_BELOW_KITKAT) {
            mWebView = new AgentWebView(mActivity);
            AgentWebConfig.WEBVIEW_TYPE = AgentWebConfig.WEBVIEW_AGENTWEB_SAFE_TYPE;
        } else {
            mWebView = new WebView(mActivity);
            AgentWebConfig.WEBVIEW_TYPE = AgentWebConfig.WEBVIEW_DEFAULT_TYPE;
        }

        return mWebView;
    }

    @Override
    public BaseIndicatorSpec offer() {
        return mBaseIndicatorSpec;
    }
}
