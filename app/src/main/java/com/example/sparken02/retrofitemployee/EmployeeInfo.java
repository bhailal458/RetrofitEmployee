package com.example.sparken02.retrofitemployee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sparken02.retrofitemployee.AllApi.WebApiCall;
import com.example.sparken02.retrofitemployee.Model.DataBean;
import com.example.sparken02.retrofitemployee.Model.MEmployee;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeInfo extends AppCompatActivity {
    public static Retrofit retrofit;
    private ArrayList<DataBean> employeeArrayList;
    private int position;
    private TextView tname,temail,tpassword,tmobile,tgender,tcity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        employeeArrayList = new ArrayList<>();
        tname = (TextView) findViewById(R.id.txtInfoname);
        temail = (TextView) findViewById(R.id.txtInfoemail);
        tpassword= (TextView) findViewById(R.id.txtInfopassword);
        tmobile = (TextView) findViewById(R.id.txtInfomobile);
        tgender = (TextView) findViewById(R.id.txtInfogender);
        tcity = (TextView) findViewById(R.id.txtInfocity);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("pos");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://music.sparkenproduct.in/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebApiCall webApiCall = retrofit.create(WebApiCall.class);
        Call<MEmployee> call = webApiCall.employee();
        call.enqueue(new Callback<MEmployee>() {
            @Override
            public void onResponse(Call<MEmployee> call, Response<MEmployee> response) {
                employeeArrayList.addAll(response.body().getData());
                getEmployeeData();
            }
            @Override
            public void onFailure(Call<MEmployee> call, Throwable t) {
            }
        });
    }
    private void getEmployeeData() {
        tname.setText("Name : "+employeeArrayList.get(position).getName());
        temail.setText("Email : "+employeeArrayList.get(position).getEmail());
        tpassword.setText("Password : "+employeeArrayList.get(position).getPassword());
        tmobile.setText("Mobile : "+employeeArrayList.get(position).getMobile());
        tgender.setText("Gender : "+employeeArrayList.get(position).getGender());
        tcity.setText("City : "+employeeArrayList.get(position).getCity());
    }
}
