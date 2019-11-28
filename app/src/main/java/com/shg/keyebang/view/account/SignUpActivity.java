package com.shg.keyebang.view.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.presenter.account.SignUpPresenter;
import com.shg.keyebang.view.BaseActivity;

public class SignUpActivity extends BaseActivity {
    private SignUpPresenter presenter;
    private EditText username;
    private EditText studentId;
    private EditText nickname;
    private EditText password;
    private EditText confirmPassword;
    private TextView toLogIn;
    private Button signUp;

    @Override
    protected void init() {
        presenter = new SignUpPresenter(this);
        username = findViewById(R.id.newUsername);
        studentId = findViewById(R.id.studentId);
        nickname = findViewById(R.id.nickname);
        password = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        toLogIn = findViewById(R.id.toLogIn);
        signUp = findViewById(R.id.signUpButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);
        init();

        signUp.setOnClickListener((v)->{
            presenter.signUp(
                    username.getText().toString(),
                    studentId.getText().toString(),
                    nickname.getText().toString(),
                    password.getText().toString(),
                    confirmPassword.getText().toString()
            );
        });

        toLogIn.setOnClickListener((v)->{
            presenter.toLogIn();
        });
    }
}
