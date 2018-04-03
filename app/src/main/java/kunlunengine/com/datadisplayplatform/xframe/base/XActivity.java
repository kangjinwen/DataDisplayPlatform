package kunlunengine.com.datadisplayplatform.xframe.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import kunlunengine.com.datadisplayplatform.xframe.common.XActivityStack;
import kunlunengine.com.datadisplayplatform.xframe.utils.permission.XPermission;


public abstract class XActivity extends AppCompatActivity implements ICallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XActivityStack.getInstance().addActivity(this);
        if (getLayoutId()>0) {
            setContentView(getLayoutId());
        }
        initData(savedInstanceState);
        initView();
    }

    /**
     * Android M 全局权限申请回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        XPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XActivityStack.getInstance().finishActivity();
    }
}
