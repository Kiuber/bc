package me.kiuber.bc.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

import me.kiuber.bc.R;
import me.kiuber.bc.ui.fragment.DynamicFragment;
import me.kiuber.bc.ui.fragment.FunctionFragment;
import me.kiuber.bc.ui.fragment.MyFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mVpContent;
    private RadioButton mRbFunction;
    private RadioButton mRbDynamic;
    private RadioButton mRbMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVpContent = (ViewPager) findViewById(R.id.vp_content);
        mRbFunction = (RadioButton) findViewById(R.id.rb_function);
        mRbDynamic = (RadioButton) findViewById(R.id.rb_dynamic);
        mRbMy = (RadioButton) findViewById(R.id.rb_my);

        mRbFunction.setOnClickListener(this);
        mRbDynamic.setOnClickListener(this);
        mRbMy.setOnClickListener(this);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FunctionFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new MyFragment());
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mVpContent.setAdapter(fragmentPagerAdapter);
        mRbFunction.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_function:
                mVpContent.setCurrentItem(0);
                break;
            case R.id.rb_dynamic:
                mVpContent.setCurrentItem(1);
                break;
            case R.id.rb_my:
                mVpContent.setCurrentItem(2);
                break;
        }
    }

    private class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
        ArrayList<Fragment> fragments;

        private FragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
//        }
    }
}
