package com.mathiasluo.designer.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.mathiasluo.designer.R;
import com.mathiasluo.designer.presenter.BasePresenter;
import com.mikepenz.iconics.context.IconicsContextWrapper;

/**
 * Created by MathiasLuo on 2016/3/2.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    protected T mPresenter;
    protected Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = creatPresenter();
        mHandler = new Handler();
        mPresenter.attachView((V) this);
        mPresenter.OnViewCreate();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.OnViewResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter.onViewDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.OnViewStop();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }


    protected T getPresenter() {
        return mPresenter;
    }

    protected abstract T creatPresenter();

    protected Handler getHandler() {
        return mHandler;
    }


}
