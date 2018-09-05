package com.example.kevin.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnEnterRegister, btnLogin;
    private EditText etEmail, etPassword;

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        btnEnterRegister = findViewById(R.id.btn_enter_register);
        btnLogin = findViewById(R.id.btn_login);
    }

    public final boolean isValidEmail(CharSequence etEmail) {
        return !TextUtils.isEmpty(etEmail) && android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail).matches();
    }

    private boolean adaYgKosong() {
        boolean ada = false;

        if (!isValidEmail(etEmail.getText().toString())) {
            ada = true;
            etEmail.setError("Silahkan isi Email anda dengan benar");
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            ada = true;
            etPassword.setError("Password tidak boleh kosong");
        }
        return ada;
    }

    public void submit(View v) {
        if (!adaYgKosong()) {
            Toast.makeText(getApplicationContext(), etEmail.getText() + "\n" + etPassword.getText(), Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    public void register(View v){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}

