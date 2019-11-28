package com.shg.keyebang.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shg.keyebang.builder.DisplayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "KYB";

    abstract protected void init();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DisplayAdapter.setCustomDensity(this, getApplication());
    }

    public void toastAndLog(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, message);
    }

    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
