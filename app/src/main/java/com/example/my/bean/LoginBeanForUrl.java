package com.example.my.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wsl on 2017/12/1.
 */

public class LoginBeanForUrl {
    /**
     * {
     * "path": null,
     * "PageIndex": 0,
     * "PageSize": 0,
     * "PageCount": 1,
     * "code": 0,
     * "msg": "成功",
     * "data": [
     * {
     * "TheServerAddress": "119.3.211.24",               //服务器地址
     * "TheServerCode": "ZD_Code",                       //服务器编号
     * "ApiAddress": "http://api.zd.119xiehui.com/",           //api地址
     * "JICAddress": "http://jic.zd.119xiehui.com/",         //jic（js）地址
     * "LoginName": null,
     * "LoginPwd": null
     * }
     * ]
     * }
     */
    public int code;
    public String msg;

    private Object path;
    private int PageIndex;
    private int PageSize;
    private int PageCount;
    @SerializedName("data")
    private List<DataBean> dataX;

    public Object getPath() {
        return path;
    }

    public void setPath(Object path) {
        this.path = path;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
    }

    public List<DataBean> getDataX() {
        return dataX;
    }

    public void setDataX(List<DataBean> dataX) {
        this.dataX = dataX;
    }

    public static class DataBean {
        /**
         * TheServerAddress : 119.3.211.24
         * TheServerCode : GXQ_Code
         * ApiAddress : http://api.gxq.119xiehui.com/
         * JICAddress : http://jic.gxq.119xiehui.com/
         * LoginName : null
         * LoginPwd : null
         */

        private String TheServerAddress;
        private String TheServerCode;
        private String ApiAddress;
        private String JICAddress;
        private Object LoginName;
        private Object LoginPwd;

        public String getTheServerAddress() {
            return TheServerAddress;
        }

        public void setTheServerAddress(String TheServerAddress) {
            this.TheServerAddress = TheServerAddress;
        }

        public String getTheServerCode() {
            return TheServerCode;
        }

        public void setTheServerCode(String TheServerCode) {
            this.TheServerCode = TheServerCode;
        }

        public String getApiAddress() {
            return ApiAddress;
        }

        public void setApiAddress(String ApiAddress) {
            this.ApiAddress = ApiAddress;
        }

        public String getJICAddress() {
            return JICAddress;
        }

        public void setJICAddress(String JICAddress) {
            this.JICAddress = JICAddress;
        }

        public Object getLoginName() {
            return LoginName;
        }

        public void setLoginName(Object LoginName) {
            this.LoginName = LoginName;
        }

        public Object getLoginPwd() {
            return LoginPwd;
        }

        public void setLoginPwd(Object LoginPwd) {
            this.LoginPwd = LoginPwd;
        }
    }


}
