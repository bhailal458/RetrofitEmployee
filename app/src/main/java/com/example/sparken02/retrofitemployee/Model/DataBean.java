package com.example.sparken02.retrofitemployee.Model;

import android.os.Parcel;
import android.os.Parcelable;

public  class DataBean implements Parcelable {
        /**
         * id : 2
         * name : Sachin
         * email : sachin@gmail.com
         * password : 81dc9bdb52d04dc20036dbd8313ed055
         * mobile : 1112223334
         * gender : Male
         * city : gandhinager
         * is_Active : yes
         * is_deleted : no
         * created_at : 2017-06-28 07:26:11
         * updated_at : 2017-06-28 07:26:11
         */

        private int id;
        private String name;
        private String email;
        private String password;
        private String mobile;
        private String gender;
        private String city;
        private String is_Active;
        private String is_deleted;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIs_Active() {
            return is_Active;
        }

        public void setIs_Active(String is_Active) {
            this.is_Active = is_Active;
        }

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.mobile);
        dest.writeString(this.gender);
        dest.writeString(this.city);
        dest.writeString(this.is_Active);
        dest.writeString(this.is_deleted);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
    }

    public DataBean() {
    }

    protected DataBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.mobile = in.readString();
        this.gender = in.readString();
        this.city = in.readString();
        this.is_Active = in.readString();
        this.is_deleted = in.readString();
        this.created_at = in.readString();
        this.updated_at = in.readString();
    }

    public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel source) {
            return new DataBean(source);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };
}