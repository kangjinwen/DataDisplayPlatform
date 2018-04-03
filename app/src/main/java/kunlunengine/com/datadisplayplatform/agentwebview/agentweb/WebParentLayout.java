package kunlunengine.com.datadisplayplatform.agentwebview.agentweb;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.FrameLayout;

import kunlunengine.com.datadisplayplatform.R;

/**
 * Created by cenxiaozhong on 2017/12/8.
 */

public class WebParentLayout extends FrameLayout implements Provider<AgentWebUIController> {
    private AgentWebUIController mAgentWebUIController = null;
    private String TAG = this.getClass().getSimpleName();
    @LayoutRes
    private int errorLayoutRes;
    @IdRes
    private int clickId = -1;
    private View mErrorView;
    private WebView mWebView;
    private FrameLayout mErrorLayout = null;

    public WebParentLayout(@NonNull Context context) {
        this(context, null);
        LogUtils.i(TAG, "WebParentLayout");
    }

    public WebParentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WebParentLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("WebParentLayout context must be activity or activity sub class .");
        }
        this.errorLayoutRes = R.layout.agentweb_error_page;
        LogUtils.i(TAG, "errorLayoutRes:" + errorLayoutRes);
    }

    void bindController(AgentWebUIController agentWebUIController) {
        LogUtils.i(TAG, "bindController:" + agentWebUIController);
        this.mAgentWebUIController = agentWebUIController;
        this.mAgentWebUIController.bindWebParent(this, (Activity) getContext());
    }

    void showPageMainFrameError() {

        View container = this.mErrorLayout;
        if (container != null) {
            container.setVisibility(View.VISIBLE);
        } else {
            createErrorLayout();
            container = this.mErrorLayout;
        }
        View clickView = null;
        if (clickId != -1 && (clickView = container.findViewById(clickId)) != null) {
            clickView.setClickable(true);
        } else {
            container.setClickable(true);
        }
    }

    private void createErrorLayout() {

        final FrameLayout mFrameLayout = new FrameLayout(getContext());
        mFrameLayout.setBackgroundColor(Color.WHITE);
        mFrameLayout.setId(R.id.mainframe_error_container_id);
        if (this.mErrorView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
            LogUtils.i(TAG, "errorLayoutRes:" + errorLayoutRes);
            mLayoutInflater.inflate(errorLayoutRes, mFrameLayout, true);
        } else {
            mFrameLayout.addView(mErrorView);
        }

        ViewStub mViewStub = (ViewStub) this.findViewById(R.id.mainframe_error_viewsub_id);
        final int index = this.indexOfChild(mViewStub);
        this.removeViewInLayout(mViewStub);
        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            this.addView(this.mErrorLayout = mFrameLayout, index, layoutParams);
        } else {
            this.addView(this.mErrorLayout = mFrameLayout, index);
        }

        mFrameLayout.setVisibility(View.VISIBLE);
        if (clickId != -1) {
            final View clickView = mFrameLayout.findViewById(clickId);
            if (clickView != null) {
                clickView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getWebView() != null) {
                            clickView.setClickable(false);
                            getWebView().reload();
                        }
                    }
                });
                return;
            } else {

                if (LogUtils.isDebug()) {
                    LogUtils.i(TAG, "ClickView is null , cannot bind accurate view to refresh or reload , your clickId:" + clickId);
                }
            }

        }

        mFrameLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getWebView() != null) {
                    mFrameLayout.setClickable(false);
                    getWebView().reload();
                }

            }
        });
    }

    void hidePageMainFrameError() {
        View mView = null;
        if ((mView = this.findViewById(R.id.mainframe_error_container_id)) != null) {
            mView.setVisibility(View.GONE);
        }
    }

    void setErrorView(@NonNull View errorView) {
        this.mErrorView = errorView;
    }

    void setErrorLayoutRes(@LayoutRes int resLayout, @IdRes int id) {
        this.clickId = id;
        if (this.clickId <= 0) {
            this.clickId = -1;
        }
        this.errorLayoutRes = resLayout;
        if (this.errorLayoutRes <= 0) {
            this.errorLayoutRes = R.layout.agentweb_error_page;
        }
    }

    @Override
    public AgentWebUIController provide() {
        return this.mAgentWebUIController;
    }


    void bindWebView(WebView view) {
        if (this.mWebView == null) {
            this.mWebView = view;
        }
    }

    public WebView getWebView() {
        return this.mWebView;
    }


}
