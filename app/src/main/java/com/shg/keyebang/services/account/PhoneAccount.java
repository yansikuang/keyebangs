package com.shg.keyebang.services.account;

import android.content.Intent;

import com.shg.keyebang.model.User;
import com.shg.keyebang.MyApplication;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

public class PhoneAccount {

    public static void sendSMS(final User user, SignUpLogInListener listener){

        user.setMobilePhoneNumber("18131416718");

        BmobSMS.requestSMSCode("18131416718", "", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    listener.phoneSuccess("发送验证码成功，短信ID：" + smsId + "\n");
                } else {
                    listener.onFailure("发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                }
            }
        });
    }

    public static void checkSMS(final User user, SignUpLogInListener listener){

        BmobUser.signOrLoginByMobilePhone(phone, code, new LogInListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {

                if (e == null) {
                    listener.phoneSuccess("短信注册或登录成功：" + bmobUser.getUsername());
                    startActivity(new Intent(UserSignUpOrLoginSmsActivity.this, UserMainActivity.class));
                } else {
                    listener.onFailure("短信注册或登录失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                }
            }
        });
    }

}
