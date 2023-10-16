package com.jump.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jump.test.data.API;
import com.jump.test.data.HTTPClient;
import com.jump.test.model.Data;
import com.jump.test.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText textViewEmail,textViewPassword;
    ProgressBar progressBar;
    API apiInterface;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnLogin = findViewById( R.id.btnLogin );
        textViewEmail = findViewById( R.id.textViewEmail );
        textViewPassword = findViewById( R.id.textViewPassword );
        progressBar = findViewById( R.id.progressBar );
        progressBar.setVisibility( View.GONE );

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textViewEmail.getText().toString();
                String password = textViewPassword.getText().toString();

                if ( email.isEmpty() ){
                    showFailure( R.string.enter_email );
                } else if( password.isEmpty() ){
                    showFailure( R.string.enter_password );
                } else {
                    login( email, password );
                }
            }
        });

    }

    public void login(String email, String password){
        showProgress();
        HTTPClient.email = email;
        HTTPClient.password = password;

        apiInterface = HTTPClient.OkHttpClient().create( API.class );

        Call<LoginResponse> call = apiInterface.login( email, password );
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if ( response.isSuccessful() ){
                    hideProgress();
                    Context context = MainActivity.this;
                    Data data = response.body().data;

                    SharedPreferences sharedPref = context.getSharedPreferences("jumptest", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("accessToken", data.user.accessToken);
                    editor.putInt("userId", data.session.userId);
                    editor.putInt("sessionId", data.session.sessionId);
                    editor.putInt("establishmentId", data.session.establishmentId);
                    editor.commit();

                    startActivity( new Intent( MainActivity.this, ManagementActvity.class ) );
                } else {
                    hideProgress();
                    showFailure( R.string.error_login );
                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                hideProgress();
                showFailure( R.string.internal_error );
            }
        });

    }

    public void manager() {
        startActivity( new Intent( MainActivity.this,ManagementActvity.class ) );
    }

    public void showProgress(){
        progressBar.setVisibility( View.VISIBLE );
    }

    public void hideProgress(){
        progressBar.setVisibility( View.GONE );
    }

    public void showFailure(Integer message){
        hideProgress();
        Toast.makeText( MainActivity.this, message, Toast.LENGTH_LONG ).show();
    }
}