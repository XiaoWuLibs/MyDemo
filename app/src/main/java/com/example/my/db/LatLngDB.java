package com.example.my.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/4/19.
 * 坐标采集存放数据库
 */

public class LatLngDB extends DataSupport {
    private int id;
    private double lat;
    private double lng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
