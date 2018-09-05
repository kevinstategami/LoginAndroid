package com.example.kevin.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    private Button btnRegister;
    private EditText etRemail, etRpassword, etCpassword, mDisplayDate, etFullname, etPhone;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRemail = findViewById(R.id.et_Remail);
        etRpassword = findViewById(R.id.et_Rpassword);
        etCpassword = findViewById(R.id.et_cpassword);
        etFullname = findViewById(R.id.et_fullname);
        mDisplayDate = findViewById(R.id.et_birthdate);
        etPhone = findViewById(R.id.et_phone);

        btnRegister = findViewById(R.id.btn_register);


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month+ " / " + dayOfMonth + " / " + year);

                String date = month + " / " +dayOfMonth+" / "+year;
                mDisplayDate.setText(date);
            }
        };
    }

    public final boolean isValidEmail(CharSequence etRemail) {
        return !TextUtils.isEmpty(etRemail) && android.util.Patterns.EMAIL_ADDRESS.matcher(etRemail).matches();
    }

    private boolean adaYgKosong() {
        boolean ada = false;

        if (!isValidEmail(etRemail.getText().toString())) {
            ada = true;
            etRemail.setError("Silahkan isi Email anda dengan benar");
        }

        if (TextUtils.isEmpty(etRpassword.getText().toString())) {
            ada = true;
            etRpassword.setError("Password tidak boleh kosong");
        }

        if (TextUtils.isEmpty(etCpassword.getText().toString())) {
            ada = true;
            etCpassword.setError("Confirm Password tidak boleh kosong");
        }

        if (TextUtils.isEmpty(etFullname.getText().toString())) {
            ada = true;
            etFullname.setError("Nama lengkap tidak boleh kosong");
        }

        if (TextUtils.isEmpty(mDisplayDate.getText().toString())) {
            ada = true;
            mDisplayDate.setError("Tanggal lahir tidak boleh kosong");
        }

        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            ada = true;
            etPhone.setError("Nomor telepon tidak boleh kosong");
        }

        if (!etCpassword.getText().toString().equals(etRpassword.getText().toString())) {
            ada = true;
            etCpassword.setError("Confirm password tidak sama dengan pasword");
        }

        return ada;
    }

    public void submit_register(View v) {
        if (!adaYgKosong()) {
            Toast.makeText(getApplicationContext(), "Selamat Datang!", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
