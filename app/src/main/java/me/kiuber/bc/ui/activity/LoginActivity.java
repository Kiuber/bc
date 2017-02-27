package me.kiuber.bc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import me.kiuber.bc.R;
import me.kiuber.bc.bean.User_Profile;
import me.kiuber.bc.util.SharedPreferenceUtil;

/**
 * Created by Kiuber on 2017/2/27 0027.
 */

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText mEtPhone;
    private EditText mEtPassword;
    private String mStrPhone;
    private String mStrPassword;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        saveUserInfo((User_Profile) msg.obj);
                        break;
                }
                return false;
            }
        });
    }

    private void initView() {
        findViewById(R.id.tv_login).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPassword = (EditText) findViewById(R.id.et_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                mStrPhone = mEtPhone.getText().toString();
                mStrPassword = mEtPassword.getText().toString();
                if (mStrPhone.equals("")) {
                    Toast.makeText(this, "手机号不能为空~", Toast.LENGTH_SHORT).show();
                } else if (mStrPassword.equals("")) {
                    Toast.makeText(this, "密码不能为空~", Toast.LENGTH_SHORT).show();
                } else {
                    verUserInfo();
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }


    private void saveUserInfo(User_Profile user_profile) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("phone");
        keys.add("uid");
        keys.add("nickname");
        keys.add("name");

        ArrayList<String> values = new ArrayList<>();
        values.add(user_profile.getPhone());
        values.add(user_profile.getUid());
        values.add(user_profile.getNickname());
        values.add(user_profile.getName());

        boolean save = SharedPreferenceUtil.putMultiple(this, "app_config", keys, values);
        if (save) {
            Toast.makeText(this, "登录成功~", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "用户信息保存失败~", Toast.LENGTH_SHORT).show();
        }
    }

    private void verUserInfo() {
        BmobQuery<User_Profile> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("phone", mStrPhone);
        bmobQuery.addWhereEqualTo("password", mStrPassword);
        bmobQuery.findObjects(new FindListener<User_Profile>() {
            @Override
            public void done(List<User_Profile> list, BmobException e) {
                Message m = new Message();
                if (e == null) {
                    if (list.size() == 0) {
                        m.what = 0;
                        m.obj = "账号或者密码错误~";
                        mHandler.sendMessage(m);
                    } else {
                        m.what = 1;
                        m.obj = list.get(0);
                        mHandler.sendMessage(m);
                    }
                } else {
                    m.what = 0;
                    m.obj = e.getMessage();
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
