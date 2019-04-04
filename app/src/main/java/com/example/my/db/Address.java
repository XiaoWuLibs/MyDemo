package com.example.my.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/1/25.
 * 数据库表-地址簿
 */

public class Address extends DataSupport {
    private int id;
    private String name;
    private String tel;
    private String city;
    private String detailAddress;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
