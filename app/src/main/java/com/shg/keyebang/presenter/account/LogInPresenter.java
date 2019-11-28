package com.shg.keyebang.presenter.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.shg.keyebang.MyApplication;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.Account;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.view.MainActivity;
import com.shg.keyebang.view.account.LogInActivity;
import com.shg.keyebang.view.account.SignUpActivity;

public class LogInPresenter extends BasePresenter {
    private LogInActivity logInActivity;

    public LogInPresenter(LogInActivity logInActivity){
        this.logInActivity = logInActivity;
    }

    public void login(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Account.login(user, new SignUpLogInListener() {
            @Override
            public void onsignupSuccess(User user, String message) {

            }

            @Override
            public void onloginSuccess(User user, String message) {
                logInActivity.toastAndLog(message);
                Account.setAccountSp(username, password);
                Intent intent = new Intent(logInActivity, MainActivity.class);
                logInActivity.startActivity(intent);
            }

            @Override
            public void onFailure(String errMessage) {
                logInActivity.toastAndLog(errMessage);
            }

            @Override
            public void phoneSuccess(String Message) {
                logInActivity.toastAndLog(Message);
            }

        });



    }

    public void autoLogin() {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("thisAccount", Context.MODE_PRIVATE);
        if(sharedPreferences == null) return;
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) return;
        login(username, password);
    }

    public void toSignUp() {
        Intent intent = new Intent(logInActivity, SignUpActivity.class);
        logInActivity.startActivity(intent);
    }


}
