package kunlunengine.com.datadisplayplatform.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kunlunengine.com.datadisplayplatform.R;
import kunlunengine.com.datadisplayplatform.banner.Banner;
import kunlunengine.com.datadisplayplatform.base.BaseWebActivity;
import kunlunengine.com.datadisplayplatform.utils.GlideImageLoader;
import kunlunengine.com.datadisplayplatform.xframe.base.XFragment;

public class HomeFragment extends XFragment {
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
    @Override
    public void initView() {
        mBanner=getView().findViewById(R.id.homeTopBanner);
        tv_kownlist=getView().findViewById(R.id.tv_kownlist1);
        mIMGList=new ArrayList();
        String mIMG1="https://wx1.sinaimg.cn/mw1024/455fd670ly1fo128oklrbj20hs089jsh.jpg";
        String mIMG2="https://wx2.sinaimg.cn/mw1024/455fd670ly1fo128oko4oj20hs07k3zh.jpg";
        String mIMG3="https://wx1.sinaimg.cn/mw1024/455fd670ly1fo128okui0j20hs0cw3za.jpg";
        mIMGList.add(mIMG1);
        mIMGList.add(mIMG2);
        mIMGList.add(mIMG3);
        tv_kownlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), BaseWebActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void initData(Bundle savedInstanceState) {

    mBanner.setImages(mIMGList).setImageLoader(new GlideImageLoader()).start();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
