package com.gasbuddy.imageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
