package com.yy.test.native_flutter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.yy.test.native_flutter.adapter.FragmentAdapter;
import com.yy.test.native_flutter.base.MyFlutterFragment;
import com.yy.test.native_flutter.fragment.NativeFragment1;
import com.yy.test.native_flutter.fragment.NativeFragment2;
import com.yy.test.native_flutter.fragment.NullFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private RadioGroup groupFootBar;

    private NativeFragment1 nativeFragment1;
    private NativeFragment2 nativeFragment2;
    private NullFragment nullFragment;  //先占位NuLLFragment
    private FragmentAdapter<Fragment>  mFragmentAdapter;

    private boolean isFirstInitFlutterView = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        initFragment();
        initViewPagerOrAdapter();
        initListener();
    }

    private void initView() {
        groupFootBar = findViewById(R.id.group_foot_bar);
        mViewPager = findViewById(R.id.viewPager);
    }

    private void initFragment() {
        nativeFragment1 = NativeFragment1.newInstance();
        nativeFragment2 = NativeFragment2.newInstance();
        nullFragment = NullFragment.newInstance();
    }

    public void initViewPagerOrAdapter(){
        mFragmentAdapter = new FragmentAdapter<>(getSupportFragmentManager());

        mFragmentAdapter.addFragment(nativeFragment1);
        mFragmentAdapter.addFragment(nativeFragment2);
        mFragmentAdapter.addFragment(nullFragment);


        mViewPager.setOffscreenPageLimit(mFragmentAdapter.getCount());
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
    }
    private int currIndex = 0;

    private void initListener() {

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    if(isFirstInitFlutterView){
                        initFlutterFragment();
                        isFirstInitFlutterView = false;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        groupFootBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.foot_bar_market:
                        currIndex = 0;
                        break;
                    case R.id.foot_bar_trans:
                        currIndex = 1;
                        break;
                    case R.id.foot_bar_otc:
                        currIndex = 2;
                        if(isFirstInitFlutterView){
                            isFirstInitFlutterView = false;
                            initFlutterFragment();
                        }
                        break;
                }
                mViewPager.setCurrentItem(currIndex);
            }
        });
    }

    /**
     * 初始化FlutterFragment
     */
    private void initFlutterFragment() {
        mFragmentAdapter.updateFragment(2, MyFlutterFragment.newInstance("tab_fragment",null));
        mFragmentAdapter.notifyDataSetChanged();
    }


}
