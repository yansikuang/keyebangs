package com.shg.keyebang.presenter.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.Account;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.view.MainActivity;
import com.shg.keyebang.view.account.LogInActivity;
import com.shg.keyebang.view.account.SignUpActivity;

public class SignUpPresenter extends BasePresenter {
    private SignUpActivity signUpActivity;

    public SignUpPresenter(SignUpActivity signUpActivity){
        this.signUpActivity = signUpActivity;
    }

    public void signUp(String username, String studentId, String nickname, String phonenumber,String password, String confirmPassword){
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(studentId) || TextUtils.isEmpty(nickname) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
            signUpActivity.toastAndLog("Some data is empty");
            return;
        }

        if(!isStudentId(studentId)){
            signUpActivity.toastAndLog("StudentId is incorrect");
            return;
        }

        if(!password.equals(confirmPassword)){
            signUpActivity.toastAndLog("NewPassword and confirmPassword are not the same");
            return;
        }

        String name = Account.getName(studentId);
        String semester = Account.getSemester(studentId);
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(semester)){
            signUpActivity.toastAndLog("StudentId is non-existent");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhonenumber(phonenumber);
        user.setStudentId(studentId);
        user.setNickname(nickname);
        user.setName(name);
        user.setSemester(semester);

        Account.signUp(user, new SignUpLogInListener() {
            @Override
            public void onsignupSuccess(User user, String message) {
                if (Account.isLogin()) Account.logOut();
                User u = new User(username, password);
                Account.login(u, new SignUpLogInListener() {
                    @Override
                    public void onsignupSuccess(User user, String message) {

                    }

                    @Override
                    public void onloginSuccess(User user, String message) {
                        signUpActivity.toastAndLog(user.getUsername());
                        Account.setAccountSp(username, password);
                        Intent intent = new Intent(signUpActivity, MainActivity.class);
                        signUpActivity.startActivity(intent);
                    }

                    @Override
                    public void onFailure(String errMessage) {
                        signUpActivity.toastAndLog(errMessage);
                    }

                    @Override
                    public void phoneSuccess(String Message) {
                        signUpActivity.toastAndLog(Message);}
                });
            }

            @Override
            public void onloginSuccess(User user, String message) {

            }

            @Override
            public void phoneSuccess(String message) {

            }

            @Override
            public void onFailure(String errMessage) {
                signUpActivity.toastAndLog(errMessage);
            }


        });

    }

    private boolean isStudentId(String studentId){
        if(studentId.length() != 7) return false;
        for (int i = studentId.length(); --i >= 0;){
            if (!Character.isDigit(studentId.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public void toLogIn() {
        Intent intent = new Intent(signUpActivity, LogInActivity.class);
        signUpActivity.startActivity(intent);
    }
}
