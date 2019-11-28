package com.shg.keyebang.view.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.presenter.account.LogInPresenter;
import com.shg.keyebang.view.BaseActivity;

public class LogInActivity extends BaseActivity {
    private LogInPresenter presenter;
    private EditText username;
    private EditText password;
    private TextView toSignUp;
    private Button logInButton;

    @Override
    protected void init() {
        presenter = new LogInPresenter(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        toSignUp = findViewById(R.id.toSignUp);
        logInButton = findViewById(R.id.loginButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        init();

        presenter.autoLogin();

        logInButton.setOnClickListener((v)->{
            presenter.login(username.getText().toString(), password.getText().toString());
        });

        toSignUp.setOnClickListener((v)->{
            presenter.toSignUp();
        });
    }
}
