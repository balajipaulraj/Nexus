package com.example.admin.bibleapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.bibleapp.Model.UserLogin;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Retrofit.APIClient;
import com.example.admin.bibleapp.Retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText inputEmail, inputNewPassword, inputOldPassword;
    private Button btnReset;
    APIInterface apiInterface;
    private ProgressBar progressBar;
    int userId;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        inputEmail = (EditText) findViewById(R.id.email);
        inputOldPassword = (EditText) findViewById(R.id.oldpassword);
        inputNewPassword = (EditText) findViewById(R.id.newpassword);
        btnReset = (Button) findViewById(R.id.reset);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        apiInterface = APIClient.getCacheEnabledRetrofit(this).create(APIInterface.class);
        sharedPreferences = getSharedPreferences("MyPrefs", 0);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = inputEmail.getText().toString().trim();
                String oldpassword = inputOldPassword.getText().toString().trim();
                String newpassword = inputNewPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                } else if (oldpassword.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Old Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                } else if (newpassword.length() < 6) {
                    Toast.makeText(getApplicationContext(), "New Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    userId = sharedPreferences.getInt("userid", 0);

                    Call<UserLogin> call = apiInterface.editpassword(userId, email, oldpassword, newpassword);
                    System.out.println("call--> " + call.toString());
                    call.enqueue(new Callback<UserLogin>() {
                        @Override
                        public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                            if (response.isSuccessful()) {
                                System.out.println("response--> " + response.body().getType());
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Reset Password successfull", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                System.out.println("responseerror--> " + response.body().getUser());
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserLogin> call, Throwable t) {
                            System.out.println("responseerror--? " + t.getMessage());
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
}
