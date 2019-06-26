package com.example.zubair.fyp_finalevalution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
  @Override
    protected  void onStart()
  {
      super.onStart();
      //For Sign Up
      //Intent In1=new Intent(this,SignUpActivity.class);
      //Intent In2=new Intent(this,Login.class);
      //startActivity(In1);

      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      Button SignUpButton=(Button)findViewById(R.id.B_SignUp);
      Button LoginButton=(Button)findViewById(R.id.B_Login);
      SignUpButton.setVisibility(View.VISIBLE);
      LoginButton.setVisibility(View.VISIBLE);

      Toast toast=Toast.makeText(getApplicationContext(),"Welcome! Hope you will have a good day today",Toast.LENGTH_SHORT);
      toast.show();


  }

  public void SignUpClick(View view)
  {
      Intent In1=new Intent(this,SignUpActivity.class);
      startActivity(In1);
  }

    public void LoginClick(View view)
    {

        Intent In2=new Intent(this,Login.class);
        startActivity(In2);

    }
}
