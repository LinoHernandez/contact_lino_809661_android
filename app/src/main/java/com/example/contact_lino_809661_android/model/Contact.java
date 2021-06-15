package com.example.contact_lino_809661_android.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "pnumber")
    private String pnumber;

    @ColumnInfo(name = "address")
    private String address;

    @Ignore
    public Contact() {
    }

    public Contact(@NonNull String name, String last_name, String email, String pnumber, String address) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.pnumber = pnumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getLast_name() { return last_name;}

    public void setLast_name(String last_name){ this.last_name = last_name;}

    public String getEmail() {return email;}

    public void setEmail(String email){this.email = email;}

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}
}
