package com.example.zubair.fyp_finalevalution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Login(View view)
    {
        Intent In1=new Intent(this,DetailsListView.class);
        startActivity(In1);
    }
}
