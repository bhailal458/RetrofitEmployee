package com.example.sparken02.retrofitemployee.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sparken02.retrofitemployee.Model.DataBean;
import com.example.sparken02.retrofitemployee.Model.MEmployee;
import com.example.sparken02.retrofitemployee.R;

import java.util.ArrayList;

/**
 * Created by sparken02 on 27/6/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<DataBean> myArrayList;


    public MyAdapter(Context mContext, ArrayList<DataBean> myArrayList) {
        this.mContext = mContext;
        this.myArrayList = myArrayList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        DataBean modelobj = myArrayList.get(position);
        holder.txtemail.setText(modelobj.getEmail());
        holder.txtcontact.setText(modelobj.getMobile());

    }

    @Override
    public int getItemCount() {
        return myArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearView;
        private TextView txtemail,txtcontact;

        public MyHolder(View view) {
            super(view);
            txtemail = (TextView) view.findViewById(R.id.txtemail);
            txtcontact = (TextView)view.findViewById(R.id.txtcontactno);
            linearView = (LinearLayout) view.findViewById(R.id.linearView);


        }
    }
}
