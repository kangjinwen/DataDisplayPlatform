package kunlunengine.com.datadisplayplatform;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import kunlunengine.com.datadisplayplatform.base.TabFragment;
import kunlunengine.com.datadisplayplatform.bottombar.BottomBarItem;
import kunlunengine.com.datadisplayplatform.bottombar.BottomBarLayout;
import kunlunengine.com.datadisplayplatform.fragment.HomeFragment;
import kunlunengine.com.datadisplayplatform.keepalive.IntentWrapper;
import kunlunengine.com.datadisplayplatform.xframe.utils.XEmptyUtils;

public class MainActivity extends AppCompatActivity {
    private ViewPager mVpContent;
    private BottomBarLayout mBottomBarLayout;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private RotateAnimation mRotateAnimation;
    private Handler mHandler = new Handler();
    private EditText msgText;
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_start:
//                TraceServiceImpl.sShouldStopService = false;
//                DaemonEnv.startServiceMayBind(TraceServiceImpl.class);
//                break;
//            case R.id.btn_white:
//                IntentWrapper.whiteListMatters(this, "保活服务的持续运行");
//                break;
//            case R.id.btn_stop:
//                TraceServiceImpl.stopService();
//                break;
//        }
    }



    public void initView() {
        mVpContent = (ViewPager) findViewById(R.id.vp_content);
        mBottomBarLayout = (BottomBarLayout) findViewById(R.id.bbl);
        initDatas();
        initListener();
    }


    private void initDatas() {

        HomeFragment homeFragment = new HomeFragment();
//        Bundle bundle1 = new Bundle();
//        bundle1.putString(TabFragment.CONTENT,"首页");
//        homeFragment.setArguments(bundle1);
        mFragmentList.add(homeFragment);

        TabFragment mapFragment = new TabFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(TabFragment.CONTENT,"地图");
        mapFragment.setArguments(bundle2);
        mFragmentList.add(mapFragment);

        TabFragment meFragment = new TabFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString(TabFragment.CONTENT,"我的");
        meFragment.setArguments(bundle3);
        mFragmentList.add(meFragment);
    }

    private void initListener() {
        mVpContent.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mBottomBarLayout.setViewPager(mVpContent);
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int position) {
                Log.i("MainActivity","position: " + position);
                if (position == 0){
                    //如果是第一个，即首页
                    if (mBottomBarLayout.getCurrentItem() == position){
                        //如果是在原来位置上点击,更换首页图标并播放旋转动画
                        bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_loading);//更换成加载图标
                        bottomBarItem.setStatus(true);

                        //播放旋转动画
                        if (mRotateAnimation == null) {
                            mRotateAnimation = new RotateAnimation(0, 360,
                                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                                    0.5f);
                            mRotateAnimation.setDuration(800);
                            mRotateAnimation.setRepeatCount(-1);
                        }
                        ImageView bottomImageView = bottomBarItem.getImageView();
                        bottomImageView.setAnimation(mRotateAnimation);
                        bottomImageView.startAnimation(mRotateAnimation);//播放旋转动画

                        //模拟数据刷新完毕
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);//更换成首页原来图标
                                bottomBarItem.setStatus(true);//刷新图标
                                cancelTabLoading(bottomBarItem);
                            }
                        },3000);
                        return;
                    }
                }

                //如果点击了其他条目
                BottomBarItem bottomItem = mBottomBarLayout.getBottomItem(0);
                bottomItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);//更换为原来的图标

                cancelTabLoading(bottomItem);//停止旋转动画
            }
        });

        mBottomBarLayout.setUnread(0,20);//设置第一个页签的未读数为20
        mBottomBarLayout.showNotify(1);//设置第三个页签显示提示的小红点
        mBottomBarLayout.setMsg(2,"NEW");//设置第四个页签显示NEW提示文字
    }

    /**停止首页页签的旋转动画*/
    private void cancelTabLoading(BottomBarItem bottomItem) {
        Animation animation = bottomItem.getImageView().getAnimation();
        if (animation != null){
            animation.cancel();
        }
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!XEmptyUtils.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }

    private void setCostomMsg(String msg){
        if (null != msgText) {
            msgText.setText(msg);
            msgText.setVisibility(android.view.View.VISIBLE);
        }
    }
    //防止华为机型未加入白名单时按返回键回到桌面再锁屏后几秒钟进程被杀
    public void onBackPressed() {
        IntentWrapper.onBackPressed(this);
    }
}
