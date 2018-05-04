package kunlunengine.com.datadisplayplatform.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kunlunengine.com.datadisplayplatform.FragmentBackHandler.FragmentBackHandler;
import kunlunengine.com.datadisplayplatform.MainActivity;
import kunlunengine.com.datadisplayplatform.R;
import kunlunengine.com.datadisplayplatform.agentwebview.agentweb.AgentWeb;
import kunlunengine.com.datadisplayplatform.agentwebview.agentweb.AgentWebSettings;
import kunlunengine.com.datadisplayplatform.agentwebview.agentweb.FragmentKeyDown;
import kunlunengine.com.datadisplayplatform.agentwebview.agentweb.WebDefaultSettingsManager;
import kunlunengine.com.datadisplayplatform.banner.Banner;
import kunlunengine.com.datadisplayplatform.base.AndroidInterface;
import kunlunengine.com.datadisplayplatform.xframe.base.XFragment;

public class HomeFragment extends XFragment implements FragmentBackHandler,FragmentKeyDown{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Banner mBanner;
    private TextView tv_kownlist;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    List<String> mIMGList;
    public static AgentWeb mAgentWeb;
    public static WebView webView;
    private ImageView mBackImageView;
    private View mLineView;
    private ImageView mFinishImageView;
    public static final String URL_KEY = "url_key";
    private ImageView mMoreImageView;
    private TextView mTitleTextView;
    private ImageView iv_back;
    private ImageView iv_finish;
    @Override
    public void initView() {
//        mBanner=getView().findViewById(R.id.homeTopBanner);
//        tv_kownlist=getView().findViewById(R.id.tv_kownlist1);
//        mIMGList=new ArrayList();
//        String mIMG1="https://wx1.sinaimg.cn/mw1024/455fd670ly1fo128oklrbj20hs089jsh.jpg";
//        String mIMG2="https://wx2.sinaimg.cn/mw1024/455fd670ly1fo128oko4oj20hs07k3zh.jpg";
//        String mIMG3="https://wx1.sinaimg.cn/mw1024/455fd670ly1fo128okui0j20hs0cw3za.jpg";
//        mIMGList.add(mIMG1);
//        mIMGList.add(mIMG2);
//        mIMGList.add(mIMG3);
//        tv_kownlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getContext(), BaseWebActivity.class);
//                startActivity(intent);
//            }
//        });
        initAgentWebView();
        iv_back = (ImageView) getView().findViewById(R.id.iv_back);
        iv_finish = (ImageView) getView().findViewById(R.id.iv_finish);
        mTitleTextView = (TextView) getView().findViewById(R.id.toolbar_title);
        mBackImageView = (ImageView) getView().findViewById(R.id.iv_back);
        mLineView = getView().findViewById(R.id.view_line);
        mFinishImageView = (ImageView) getView().findViewById(R.id.iv_finish);
        mTitleTextView = (TextView) getView().findViewById(R.id.toolbar_title);
        mBackImageView.setOnClickListener(mOnClickListener);
        mFinishImageView.setOnClickListener(mOnClickListener);
        mMoreImageView = (ImageView) getView().findViewById(R.id.iv_more);
        mMoreImageView.setOnClickListener(mOnClickListener);
    }

    private void initAgentWebView() {
        mAgentWeb = AgentWeb.with(getActivity())//这里需要把 Activity 、 和 Fragment  同时传入
                .setAgentWebParent((ViewGroup) getView(), new LinearLayout.LayoutParams(-1, -1))// 设置 AgentWeb 的父控件 ， 这里的view 是 LinearLayout ， 那么需要传入 LinearLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .setAgentWebWebSettings(getSettings())//设置 AgentWebSettings
                .setWebViewClient(mWebViewClient)//WebViewClient ， 与WebView 一样
//                .setReceivedTitleCallback(mCallback) //标题回调
//                .addDownLoadResultListener(mDownLoadResultListener) //下载回调
//                .openParallelDownload()//打开并行下载 , 默认串行下载
//                .setNotifyIcon(R.mipmap.download)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK) //注意这里开启 strict 模式 ， 设备低于 4.2 情况下回把注入的 Js 全部清空掉 ， 这里推荐使用 onJsPrompt 通信
                .createAgentWeb()//
                .ready()//
//                .go(MainActivity.indexUrl);
//                .go("http://192.168.0.102:8080/jeecg/appController/app/loginUrl.do?userName=admin");
                .go("http://www.qq.com");
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(mAgentWeb, getContext()));
        webView = mAgentWeb.getWebCreator().getWebView();
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {

                case R.id.iv_back:

                    if (!mAgentWeb.back())
                        HomeFragment.this.getActivity().finish();

                    break;
                case R.id.iv_finish:
//                    mAgentWeb.getLoader().loadUrl(getUrl());
//                    MainActivity.getInstance().mFl_mainactivity_bottom.setVisibility(View.VISIBLE);
//                    webView.clearHistory();
//                    boolean isWebBack=mAgentWeb.back();
//                    if (!isWebBack)
//                        FirstFragment.this.getActivity().finish();
//                    MainActivity.initHomeFragment();
                    MainActivity mainActivity=MainActivity.getInstance();
                    mainActivity.onClick(v);
                    break;
            }
        }
    };
    @Override
    public void initData(Bundle savedInstanceState) {

//        mBanner.setImages(mIMGList).setImageLoader(new GlideImageLoader()).start();
    }

    public AgentWebSettings getSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    protected WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl() + "");
//            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            //intent:// scheme的处理 如果返回false ， 则交给 DefaultWebClient 处理 ， 默认会打开该Activity  ， 如果Activity不存在则跳到应用市场上去.  true 表示拦截
            //例如优酷视频播放 ，intent://play?...package=com.youku.phone;end;
            //优酷想唤起自己应用播放该视频 ， 下面拦截地址返回 true  则会在应用内 H5 播放 ，禁止优酷唤起播放该视频， 如果返回 false ， DefaultWebClient  会根据intent 协议处理 该地址 ， 首先匹配该应用存不存在 ，如果存在 ， 唤起该应用播放 ， 如果不存在 ， 则跳到应用市场下载该应用 .
            if (url.startsWith("intent://") && url.contains("com.youku.phone")) {
                return true;
            }
            /*else if (isAlipay(view, url))   //1.2.5开始不用调用该方法了 ，只要引入支付宝sdk即可 ， DefaultWebClient 默认会处理相应url调起支付宝
                return true;*/
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String gotoUrl, Bitmap favicon) {
//            Log.e("onPageStarted", "gotoUrl:" + gotoUrl + " onPageStarted  target:" + getUrl());
//            boolean isContain = gotoUrl.contains("/app/loginUrl.do?userName=");
//            if (gotoUrl.equals(getUrl()) || isContain) {
//                MainActivity.showBottom();
//                iv_back.setVisibility(View.GONE);
//                iv_finish.setVisibility(View.GONE);
//            } else {
//                MainActivity.hideBottom();
//                iv_back.setVisibility(View.VISIBLE);
//                iv_finish.setVisibility(View.VISIBLE);
//            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            String title = view.getTitle();
//            if (mTitleTextView != null && !TextUtils.isEmpty(title)) {
//                if (title.length() > 10)
//                { title = title.substring(0, 10) + "...";
//                    mTitleTextView.setText(title);}
//            }
            super.onPageFinished(view, url);
        }
    };

    @Override
    public boolean onBackPressed() {
        if (mAgentWeb.back()) {
            //物理返回键
            return true;
        } else {
            // 如果不包含子Fragment
            // 或子Fragment没有物理back需求
            // 可如直接 return false;
            // 注：如果Fragment/Activity 中可以使用ViewPager 代替 this
            return false;
        }
    }

    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
