package com.company.insta.instaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    LinearLayout mLoginContainer;
    AnimationDrawable animationDrawable;

    EditText username_et, password_et;
    TextView sign_up_btn, forgot_pass_btn;
    Button login_btn;
    ProgressDialog mProgressDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginContainer = (LinearLayout) findViewById(R.id.login_container);
       animationDrawable = (AnimationDrawable) mLoginContainer.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);

        username_et = (EditText) findViewById(R.id.user_name);
        login_btn = (Button) findViewById(R.id.login_btn);
        password_et = (EditText) findViewById(R.id.user_password);
        sign_up_btn = (TextView) findViewById(R.id.sign_up_btn);
        forgot_pass_btn = (TextView) findViewById(R.id.forgot_pass_btn);
        mProgressDailog = new ProgressDialog(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent signUpIntent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(signUpIntent);
            }
        });

        forgot_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void logIn()
    {
        mProgressDailog.setMessage("Please wait....");
        mProgressDailog.setTitle("LogIn");
        mProgressDailog.show();
       final String username= username_et.getText().toString();
      final String password =  password_et.getText().toString();

      if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
      {
          username_et.setError("Please fill this field");
          username_et.requestFocus();
          mProgressDailog.dismiss();
          return;
      }if(TextUtils.isEmpty(password))
    {
        password_et.setError("Please fill this field");
        password_et.requestFocus();
        mProgressDailog.dismiss();
        return;
    }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLS.login_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (!jsonObject.getBoolean("error"))
                    {
                        mProgressDailog.dismiss();
                     JSONObject jsonObjectUser = jsonObject.getJSONObject("user");

                     User user = new User(jsonObjectUser.getInt("id"), jsonObjectUser.getString("email"), jsonObjectUser.getString("username"));


                     //store user data inside shared prefernces
                        SharedPreferenceManager.getInstance(getApplicationContext()).storeUserData(user);


                        //let user in
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                 mProgressDailog.dismiss();
            }
        }


        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> authenticationVariables  = new HashMap<>();
                authenticationVariables.put("username", username);
                authenticationVariables.put("password", password);

                return authenticationVariables;
            }
        };
      VolleyHandler.getInstance(getApplicationContext()).addRequestToQueue(stringRequest);

    }




    @Override
    protected void onResume() {
        super.onResume();

        if(animationDrawable != null && !animationDrawable.isRunning())
        {
            animationDrawable.start();
        }


    }
    @Override
    protected void onPause() {
        super.onPause();

        if(animationDrawable != null && animationDrawable.isRunning())
        {
            animationDrawable.stop();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        boolean isUserLoggedIn =SharedPreferenceManager.getInstance(getApplicationContext()).isUserLoggedIn();
        if (isUserLoggedIn)
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else{

        }
    }
}
