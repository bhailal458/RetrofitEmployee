package com.example.sparken02.retrofitemployee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

public class EmployeeEdit extends AppCompatActivity {
    public static Retrofit retrofit;
    private EditText eupdatename,eupdateemail,eupdatepassword,eupdatemobile;
    private RadioGroup radioGroupupdate;
    private RadioButton radioButtonupdate;
    private RadioButton rmale,rfemale;
    private Spinner spinnerCityupdate;
    private Button btnUpdate;
    private MEmployee employeeObj;
    private int id;
    private String message;
    private ArrayList<DataBean> employeeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);

        Intent intent = getIntent();
        DataBean empObj = intent.getExtras().getParcelable("Data");



        eupdatename = (EditText) findViewById(R.id.edtUpdatename);
        eupdateemail = (EditText) findViewById(R.id.edtUpdateemail);
        eupdatepassword = (EditText) findViewById(R.id.edtUpdatepassword);
        eupdatemobile = (EditText) findViewById(R.id.edtUpdatemobile);
        radioGroupupdate = (RadioGroup) findViewById(R.id.radiogroupUpdate);
        rmale = (RadioButton) findViewById(R.id.rdUpdatemale);
        rfemale = (RadioButton) findViewById(R.id.rdUpdatemale);
        spinnerCityupdate = (Spinner) findViewById(R.id.spUpdatecity);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        employeeObj = new MEmployee();
        employeeArrayList = new ArrayList<>();

        id = empObj.getId();
        eupdatename.setText(empObj.getName());
        eupdateemail.setText(empObj.getEmail());
        eupdatepassword.setText(empObj.getPassword());
        eupdatemobile.setText(empObj.getMobile());
        String gen = empObj.getGender().toString().trim();
        if (gen.equals("Male"))
           rmale.setChecked(true);
        else
        rfemale.setChecked(true);
//        tcity.setText(employeeArrayList.get(position).getCity());

          //Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectGender = radioGroupupdate.getCheckedRadioButtonId();
                radioButtonupdate = (RadioButton)findViewById(selectGender);

                retrofit = new Retrofit.Builder()
                        .baseUrl("http://music.sparkenproduct.in/public/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                WebApiCall webApiCall = retrofit.create(WebApiCall.class);
                Call<MEmployee> call = webApiCall.updateEmployee(id,
                        eupdatename.getText().toString().trim(),
                        eupdateemail.getText().toString().trim(),
                        eupdatepassword.getText().toString().trim(),
                        eupdatemobile.getText().toString().trim(),
                        radioButtonupdate.getText().toString().trim(),
                        spinnerCityupdate.getSelectedItem().toString().trim());
                call.enqueue(new Callback<MEmployee>() {
                    @Override
                    public void onResponse(Call<MEmployee> call, Response<MEmployee> response) {
                        if(response.isSuccessful()) {

                            employeeObj = response.body();
                            message = employeeObj.getMessage();
                            Toast.makeText(EmployeeEdit.this, ""+message, Toast.LENGTH_SHORT).show();
                            Intent addintent = new Intent(EmployeeEdit.this, MainActivity.class);
                            startActivity(addintent);
                        }
                    }
                    @Override
                    public void onFailure(Call<MEmployee> call, Throwable t) {
                        Log.i("data","callled");
                    }
                });
            }
        });
    }

}
