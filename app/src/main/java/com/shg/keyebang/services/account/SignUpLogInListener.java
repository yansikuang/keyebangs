package com.shg.keyebang.services.account;

import com.shg.keyebang.model.User;

public abstract class SignUpLogInListener {
    public abstract void onsignupSuccess(User user, String message);

    public abstract void onloginSuccess(User user,String message);

    public abstract void phoneSuccess(String message);

    public abstract void onFailure(String errMessage);
}
