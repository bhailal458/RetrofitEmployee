package com.example.sparken02.retrofitemployee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.sparken02.retrofitemployee.AllApi.WebApiCall;
import com.example.sparken02.retrofitemployee.Model.MEmployee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmploeeAdd extends AppCompatActivity {
    public static Retrofit retrofit;
    private EditText ename,eemail,epassword,emobile;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Spinner spinnerCity;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emploee);


        ename = (EditText) findViewById(R.id.edtAddname);
        eemail = (EditText) findViewById(R.id.edtAddemail);
        epassword = (EditText) findViewById(R.id.edtAddpassword);
        emobile = (EditText) findViewById(R.id.edtAddmobile);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        spinnerCity = (Spinner) findViewById(R.id.spAddcity);
//        radioButton = (RadioButton) findViewById()
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectGender = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selectGender);
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.sparkenproduct.in/public/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                WebApiCall webApiCall = retrofit.create(WebApiCall.class);
                Call<MEmployee> call = webApiCall.addEmployee(ename.getText().toString().trim(),
                                                              eemail.getText().toString().trim(),
                                                              epassword.getText().toString().trim(),
                                                              emobile.getText().toString().trim(),
                                                              radioButton.getText().toString().trim(),
                                                              spinnerCity.getSelectedItem().toString().trim());
                call.enqueue(new Callback<MEmployee>() {
                    @Override
                    public void onResponse(Call<MEmployee> call, Response<MEmployee> response) {

                        MEmployee messageobj = new MEmployee();
                        if(response.isSuccessful()) {
                            messageobj = response.body();
                            Intent addintent = new Intent(EmploeeAdd.this, MainActivity.class);
                            startActivity(addintent);
                        }
                    }
                    @Override
                    public void onFailure(Call<MEmployee> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });

    }
}
