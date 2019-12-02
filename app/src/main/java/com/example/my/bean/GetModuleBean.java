package com.example.my.bean;

import java.util.List;

/**
 * Created by  wsl
 * on 2019/11/27 16:54
 */
public class GetModuleBean {
    /**
     * code : 0
     * message : 成功
     * data : {"ssid":"zhongtie","time":[{"start_time":"08:00","end_time":"9:00"},{"start_time":"12:00","end_time":"12:30"},{"start_time":"13:00","end_time":"13:30"},{"start_time":"18:00","end_time":"19:00"}],"lan1":114.465,"lat1":38.0298,"lan2":114.468157,"lat2":38.029011}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ssid : zhongtie
         * time : [{"start_time":"08:00","end_time":"9:00"},{"start_time":"12:00","end_time":"12:30"},{"start_time":"13:00","end_time":"13:30"},{"start_time":"18:00","end_time":"19:00"}]
         * lan1 : 114.465
         * lat1 : 38.0298
         * lan2 : 114.468157
         * lat2 : 38.029011
         */

        private String ssid;
        private double lan1;
        private double lat1;
        private double lan2;
        private double lat2;
        private List<TimeBean> time;

        public String getSsid() {
            return ssid;
        }

        public void setSsid(String ssid) {
            this.ssid = ssid;
        }

        public double getLan1() {
            return lan1;
        }

        public void setLan1(double lan1) {
            this.lan1 = lan1;
        }

        public double getLat1() {
            return lat1;
        }

        public void setLat1(double lat1) {
            this.lat1 = lat1;
        }

        public double getLan2() {
            return lan2;
        }

        public void setLan2(double lan2) {
            this.lan2 = lan2;
        }

        public double getLat2() {
            return lat2;
        }

        public void setLat2(double lat2) {
            this.lat2 = lat2;
        }

        public List<TimeBean> getTime() {
            return time;
        }

        public void setTime(List<TimeBean> time) {
            this.time = time;
        }

        public static class TimeBean {
            /**
             * start_time : 08:00
             * end_time : 9:00
             */

            private String start_time;
            private String end_time;

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }
        }
    }
}
