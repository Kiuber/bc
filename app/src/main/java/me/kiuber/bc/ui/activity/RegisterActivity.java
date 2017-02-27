package me.kiuber.bc.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import me.kiuber.bc.R;
import me.kiuber.bc.bean.User_Profile;

/**
 * Created by Kiuber on 2017/2/27 0027.
 */

public class RegisterActivity extends Activity implements View.OnClickListener {

    private AppCompatEditText mAcetPhone;
    private TextView mTvSendCode;
    private AppCompatEditText mAcetCode;
    private TextView mTvVerCode;
    private AppCompatEditText mAcetPassword;
    private TextView mTvSavePassword;
    private String mStrPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initSMSHandler();
    }

    private void initSMSHandler() {
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(RegisterActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        mAcetPhone.setVisibility(View.GONE);
                        mTvSendCode.setVisibility(View.GONE);

                        mTvVerCode.setVisibility(View.VISIBLE);
                        mAcetCode.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mAcetCode.setVisibility(View.GONE);
                        mTvVerCode.setVisibility(View.GONE);

                        mAcetPassword.setVisibility(View.VISIBLE);
                        mTvSavePassword.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message m = new Message();
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        m.what = 1;
                        handler.sendMessage(m);
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        m.what = 2;
                        handler.sendMessage(m);
                    } else {
                    }
                } else {
                    m.what = 0;
                    ((Throwable) data).printStackTrace();
                    String message = ((Throwable) data).getMessage();
                    try {
                        JSONObject jsonObject = new JSONObject(message);
                        m.obj = jsonObject.getString("detail");
                        handler.sendMessage(m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);


    }

    private void initView() {
        mAcetPhone = (AppCompatEditText) findViewById(R.id.acet_phone);
        mAcetPhone.setOnClickListener(this);
        mTvSendCode = (TextView) findViewById(R.id.tv_send_code);
        mTvSendCode.setOnClickListener(this);
        mAcetCode = (AppCompatEditText) findViewById(R.id.acet_code);
        mAcetCode.setOnClickListener(this);
        mTvVerCode = (TextView) findViewById(R.id.tv_ver_code);
        mTvVerCode.setOnClickListener(this);
        mAcetPassword = (AppCompatEditText) findViewById(R.id.acet_password);
        mAcetPassword.setOnClickListener(this);
        mTvSavePassword = (TextView) findViewById(R.id.tv_save_password);
        mTvSavePassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send_code:
                String phone = mAcetPhone.getText().toString();
                if (!phone.equals("")) {
                    mStrPhone = mAcetPhone.getText().toString();
                    isRegister();
                } else {
                    Toast.makeText(this, "请先输入手机号~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_ver_code:
                SMSSDK.submitVerificationCode("86", mStrPhone, mAcetCode.getText().toString());
                break;
            case R.id.tv_save_password:
                saveUserInfo();
                break;
        }
    }

    private void isRegister() {
        BmobQuery<User_Profile> bmobQuery = new BmobQuery<User_Profile>();
        bmobQuery.addWhereEqualTo("phone", mStrPhone);
        bmobQuery.findObjects(new FindListener<User_Profile>() {
            @Override
            public void done(List<User_Profile> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        SMSSDK.getVerificationCode("86", mStrPhone);
                    } else {
                        Toast.makeText(RegisterActivity.this, "该手机号已注册~", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserInfo() {
        User_Profile user_profile = new User_Profile();
        user_profile.setPhone(mStrPhone);
        user_profile.setPassword(mAcetPassword.getText().toString());
        user_profile.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(RegisterActivity.this, "注册成功~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
