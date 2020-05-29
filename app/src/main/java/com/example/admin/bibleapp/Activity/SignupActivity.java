package com.example.admin.bibleapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.bibleapp.Model.Reg;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Retrofit.APIClient;
import com.example.admin.bibleapp.Retrofit.APIInterface;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputfirst, inputLast, inputPhone;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    APIInterface apiInterface;
    private String fcmnewToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        apiInterface = APIClient.getCacheEnabledRetrofit(this).create(APIInterface.class);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SignupActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                fcmnewToken = instanceIdResult.getToken();
                Log.e("newToken", fcmnewToken);

            }
        });

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputfirst = (EditText) findViewById(R.id.firstname);
        inputLast = (EditText) findViewById(R.id.lastname);
        inputPhone = (EditText) findViewById(R.id.phone);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = inputEmail.getText().toString().trim();
                String firstname = inputfirst.getText().toString().trim();
                String lastname = inputLast.getText().toString().trim();
                String phone = inputPhone.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                } else if (firstname.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter full name!", Toast.LENGTH_SHORT).show();
                } else if (lastname.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter last name!", Toast.LENGTH_SHORT).show();
                } else if (phone.length() < 10) {
                    Toast.makeText(getApplicationContext(), "Enter phone minimum 10 characters!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    Call<Reg> call = apiInterface.signup(email, password, firstname, lastname, phone, fcmnewToken);
                    call.enqueue(new Callback<Reg>() {
                        @Override
                        public void onResponse(Call<Reg> call, Response<Reg> response) {
                            if (response.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), (response.body() != null ? response.body().getMsg() : null) + "and You Userid" + response.body().getUserId(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Reg> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                            call.cancel();
                        }
                    });
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}