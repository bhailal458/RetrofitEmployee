package com.example.sparken02.retrofitemployee.Model;

import java.util.List;

/**
 * Created by sparken02 on 28/6/17.
 */

public class MEmployee {

    /**
     * message : Employee All List
     * data : [{"id":2,"name":"Sachin","email":"sachin@gmail.com","password":"81dc9bdb52d04dc20036dbd8313ed055","mobile":"1112223334","gender":"Male","city":"gandhinager","is_Active":"yes","is_deleted":"no","created_at":"2017-06-28 07:26:11","updated_at":"2017-06-28 07:26:11"},{"id":3,"name":"Sachin","email":"sachin@gmail.com","password":"81dc9bdb52d04dc20036dbd8313ed055","mobile":"1112223334","gender":"Male","city":"gandhinager","is_Active":"yes","is_deleted":"no","created_at":"2017-06-28 07:35:11","updated_at":"2017-06-28 07:35:11"}]
     */

    private String message;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

}
