package me.kiuber.bc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import me.kiuber.bc.R;
import me.kiuber.bc.ui.activity.LoginActivity;
import me.kiuber.bc.util.AppUtil;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Kiuber on 2017/2/27 0027.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView mTvLogin;
    private TextView mTvSina;
    private TextView mTvQQ;
    private TextView mTvWechat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        initView();
        isUserLogin();
        return view;
    }

    private void isUserLogin() {
        String nickname = AppUtil.isLogin(getContext());
        if (nickname != null) {
            loginSuccessSet(nickname);
        }
    }

    private void loginSuccessSet(String nickname) {
        mTvLogin.setText(nickname);
        mTvLogin.setClickable(false);

        ViewParent parent = mTvSina.getParent();
        LinearLayout parent1 = (LinearLayout) parent;
        parent1.setVisibility(View.GONE);
    }

    private void initView() {
        mTvLogin = (TextView) view.findViewById(R.id.tv_login);
        mTvLogin.setOnClickListener(this);

        mTvSina = (TextView) view.findViewById(R.id.tv_sina);
        mTvQQ = (TextView) view.findViewById(R.id.tv_qq);
        mTvWechat = (TextView) view.findViewById(R.id.tv_wechat);
        mTvSina.setOnClickListener(this);
        mTvQQ.setOnClickListener(this);
        mTvWechat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                startActivityForResult(new Intent(getContext(), LoginActivity.class), 1);
                break;
            case R.id.tv_sina:
                Toast.makeText(getContext(), "客官别急，该功能正在开发中~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_qq:
                Toast.makeText(getContext(), "客官别急，该功能正在开发中~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_wechat:
                Toast.makeText(getContext(), "客官别急，该功能正在开发中~", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String nickname = data.getStringExtra("nickname");
                loginSuccessSet(nickname);
            }
        }
    }
}
