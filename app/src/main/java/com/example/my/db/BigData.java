package com.example.my.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/1/25.
 * 数据库表-大数据分析,根据选择数据的次数来推荐热搜词
 */

public class BigData extends DataSupport {
    private int id;
    private String content;
    private int clickNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }
}
