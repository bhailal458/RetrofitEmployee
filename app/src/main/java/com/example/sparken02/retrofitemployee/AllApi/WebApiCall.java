package com.example.sparken02.retrofitemployee.AllApi;

import com.example.sparken02.retrofitemployee.Model.MEmployee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sparken02 on 27/6/17.
 */

public interface WebApiCall {

    @GET("getAllEmployee")
    Call<MEmployee> employee();

    @DELETE("deleteEmployee/{id}")
    Call<MEmployee> deleteEmployeeById(@Path("id") int id);

    @FormUrlEncoded
    @POST("updateEmployee")
    Call<MEmployee>updateEmployee(@Field("id") int id,
                                  @Field("name")String name,
                                  @Field("email")String email,
                                  @Field("password")String password,
                                  @Field("mobile")String mobile,
                                  @Field("gender")String gender,
                                  @Field("city")String city);

    @FormUrlEncoded
    @POST("addEmployee")
    Call <MEmployee>addEmployee(@Field("name")String name,
                     @Field("email")String email,
                     @Field("password")String password,
                     @Field("mobile")String mobile,
                     @Field("gender")String gender,
                     @Field("city")String city);
}
