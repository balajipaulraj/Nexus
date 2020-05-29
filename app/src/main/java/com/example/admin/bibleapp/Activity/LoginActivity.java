package com.example.admin.bibleapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.admin.bibleapp.Model.UserLogin;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Retrofit.APIClient;
import com.example.admin.bibleapp.Retrofit.APIInterface;
import com.example.admin.bibleapp.Retrofit.SideMenu;
import com.example.admin.bibleapp.popupWindow.bibleselection;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    APIInterface apiInterface;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
//        tvTitle.setText("Login");

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        apiInterface = APIClient.getCacheEnabledRetrofit(this).create(APIInterface.class);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                } else {
                    Call<UserLogin> call = apiInterface.login(email, password);
                    call.enqueue(new Callback<UserLogin>() {
                        @Override
                        public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                            try {
                                if (response.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    UserLogin userLogin = response.body();
                                    String type = userLogin.getType();
                                    if (type.equals("success")) {
                                        sharedPreferences = getSharedPreferences("MyPrefs", 0);
                                        SharedPreferences.Editor date_store = sharedPreferences.edit();
                                        List<user> user = userLogin.getUser();
                                        System.out.println("user--> " + user.get(0).getUserId());
                                        date_store.putInt("userid", user.get(0).getUserId());
                                        Gson gson = new Gson();
                                        String json = gson.toJson(user);
                                        date_store.putString("userdata", json);
                                        date_store.putBoolean("firsttime", false);
                                        date_store.apply();
                                        SideMenu sideMenu = new SideMenu();
                                        sideMenu.getMenuItem(LoginActivity.this, 1);
                                        startActivity(new Intent(LoginActivity.this, bibleselection.class));
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onFailure(Call<UserLogin> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            try {

                                Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                                call.cancel();
                            } catch (Exception e) {

                            }
                        }
                    });
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}

