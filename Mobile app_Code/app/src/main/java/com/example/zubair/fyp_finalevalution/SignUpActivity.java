package com.example.zubair.fyp_finalevalution;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void SignUp(View view)
    {
        Toast toast=Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_SHORT);
        toast.show();
       final EditText EDIT1 =(EditText)findViewById(R.id.E_Email);

        final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
        dialog.setTitle("Account Created");
        dialog.setMessage("YOUR ACCOUNT HAS BEEN CREATED");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("Give email argumant", "GiveEmailPassword");
                    sender.sendMail("New Account Created",
                            "New Account has been created",
                            "szubair1833@gmail.com",
                            EDIT1.getText().toString());
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.v("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}
