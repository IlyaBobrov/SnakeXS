package com.example.viperxs;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    String login;
    String pass;
    String email;
    String phone;

    /*protected User(Parcel in) {
        login = in.readString();
        pass = in.readString();
        email = in.readString();
        phone = in.readString();
    }*/

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            String login   = in.readString();
            String email   = in.readString();
            String phone   = in.readString();
            String pass    = in.readString();
            return new User(login, email, phone, pass);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(String login, String email, String phone, String pass) {
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(pass);
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPass() {
        return pass;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
