package me.kiuber.bc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.kiuber.bc.R;
import me.kiuber.bc.ui.activity.LoginActivity;

/**
 * Created by Kiuber on 2017/2/27 0027.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        initView();
        return view;
    }

    private void initView() {
        TextView mTvLogin = (TextView) view.findViewById(R.id.tv_login);
        mTvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }
    }
}
