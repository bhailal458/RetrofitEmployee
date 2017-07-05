package com.example.sparken02.retrofitemployee;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.sparken02.retrofitemployee.Adapter.MyAdapter;
import com.example.sparken02.retrofitemployee.AllApi.WebApiCall;
import com.example.sparken02.retrofitemployee.Model.DataBean;
import com.example.sparken02.retrofitemployee.Model.MEmployee;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    public static Retrofit retrofit;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DataBean> employeeArrayList;
    private MyAdapter mAdapter;
    private Context context;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.l_coordinate);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //add divider recycclerView
        mRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.CYAN)
                        .build());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setOnClickListener((View.OnClickListener) context);
        employeeArrayList = new ArrayList<>();

        //API Connection
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
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<MEmployee> call, Throwable t) {

            }
        });

        //Set Adapter
        mAdapter = new MyAdapter(MainActivity.this, employeeArrayList);
        mRecyclerView.setAdapter(mAdapter);

        //Fab Button OnClick
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fabintent = new Intent(MainActivity.this,EmploeeAdd.class);
                startActivity(fabintent);
            }
        });

        //  OnClick for RecyclerView
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever

                        Intent infointent = new Intent(MainActivity.this,EmployeeInfo.class);
                        infointent.putExtra("pos",position);
                        startActivity(infointent);
                    }
                    @Override
                    public void onItemLongClick(View view, int position) {

                        displayDialog(position);   //Call display dialog
                    }
                })
        );
    }

    // Dialog DELETE and EDIT

    private void displayDialog(final int position) {
        String[] array = {"DELETE","EDIT"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose")
                .setItems(array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:

                                retrofit = new Retrofit.Builder()
                                        .baseUrl("http://music.sparkenproduct.in/public/api/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                WebApiCall webApiCall = retrofit.create(WebApiCall.class);
//                                Call<MEmployee> call = webApiCall.deleteEmployeeById(position);

                                Call<MEmployee> call = webApiCall.deleteEmployeeById(employeeArrayList.get(position).getId());
                                call.enqueue(new Callback<MEmployee>() {
                                                 @Override
                                                 public void onResponse(Call<MEmployee> call, Response<MEmployee> response) {
//                                                     employeeArrayList.addAll(response.body().getData());
                                              //refresh activity remaining...........
                                                     employeeArrayList.remove(position);
                                                     mAdapter.notifyDataSetChanged();



//                                                     Intent deleteintent = new Intent(MainActivity.this,MainActivity.class);
//                                                     startActivity(deleteintent);
                                                 }
                                                 @Override
                                                 public void onFailure(Call<MEmployee> call, Throwable t) {
                                                 }
                                             });
                                    Log.i("TAG","DELETED");

                                //Display message in Snackbar
                                Snackbar snackbar = Snackbar
                                        .make(coordinatorLayout, "Message is deleted", Snackbar.LENGTH_LONG)
                                        .setAction("UNDO", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                                                snackbar1.show();
                                            }
                                        });
                                snackbar.show();
                                break;
                            case 1:

                                DataBean empObj = employeeArrayList.get(position);
                                Intent editintent = new Intent(MainActivity.this,EmployeeEdit.class);

                                editintent.putExtra("Data",empObj);
//                                MEmployee.DataBean student = getIntent().getParcelableExtra("Data");


                                startActivity(editintent);

                                Log.i("TAG","EDIT");
                                break;
                        }
                    }
                });
        builder.show();
    }
}
