package com.shg.keyebang.services.account;

import android.content.Context;
import android.content.SharedPreferences;

import com.shg.keyebang.MyApplication;
import com.shg.keyebang.model.User;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.BmobSMS;

public class Account {

    public static void signUp(final User user, SignUpLogInListener listener){

        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User u, BmobException e) {
                if(e == null) listener.onsignupSuccess(u, u.getUsername());
                else listener.onFailure(e.getErrorCode() + ": " + e.getMessage());
            }
        });
    }

    public static void getSMS(final User user,SignUpLogInListener listener){

        user.setMobilePhoneNumber();

        BmobSMS.requestSMSCode(phonenumber, "", new QueryListener<Integer>() {
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


    public static void login(final User user, SignUpLogInListener listener){

        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e == null) listener.onloginSuccess(user, user.getUsername());
                else listener.onFailure(e.getErrorCode() + ": " + e.getMessage());
            }
        });
    }


    public static void logOut(){
        SharedPreferences.Editor editor= MyApplication.getContext().getSharedPreferences("thisAccount", Context.MODE_PRIVATE).edit();
        editor.clear().apply();
        User.logOut();
    }

    public static boolean isLogin(){
        return User.isLogin();
    }

    public static String getSemester(String studentId){
        return "大一下";
    }

    public static String getName(String studentId){
        return "小课同学";
    }

    public static void setAccountSp(String username, String password){
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("thisAccount", Context.MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }
}
