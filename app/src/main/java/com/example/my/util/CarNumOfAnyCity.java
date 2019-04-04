package com.example.my.util;

/**
 * Created by Administrator on 2018/4/3.
 */

public class CarNumOfAnyCity {
    //河北
    public static String getHBCarNumTagOfAnyCity(String city) {
        switch (city) {
            case "石家庄市"://冀A
                return "冀A ";
            case "辛集市"://冀A
                return "冀A ";
            case "唐山市"://冀B
                return "冀B ";
            case "秦皇岛市"://冀C
                return "冀C ";
            case "邯郸市"://冀D
                return "冀D ";
            case "邢台市"://冀E
                return "冀E ";
            case "保定市"://冀F
                return "冀F ";
            case "张家口市"://冀G
                return "冀G ";
            case "承德市"://冀H
                return "冀H ";
            case "衡水市"://冀T
                return "冀T ";
            case "沧州市"://冀J
                return "冀J ";
            default://冀
                return "冀 ";
        }
    }

    //山西
    public static String getShXCarNumTagOfAnyCity(String city) {
        switch (city) {
            case "太原市"://晋A
                return "晋A ";
            case "大同市"://晋B
                return "晋B ";
            case "阳泉市"://晋C
                return "晋C ";
            case "长治市"://晋D
                return "晋D ";
            case "晋城市"://晋E
                return "晋E ";
            case "朔州市"://晋F
                return "晋F ";
            case "忻州市"://晋H
                return "晋H ";
            case "吕梁地区"://晋J
                return "晋J ";
            case "晋中市"://晋K
                return "晋K ";
            case "临汾市"://晋L
                return "晋L ";
            case "运城市"://晋M
                return "晋M ";
            default://晋
                return "晋 ";
        }
    }
}
