package com.example.my.bean;

/**
 * Created by  wsl
 * on 2019/3/19 14:34
 */
public class IbeaconBean {
    public String key;
    public String mac;
    public String rssi;

    public IbeaconBean(String key, String mac, String rssi) {
        this.key = key;
        this.mac = mac;
        this.rssi = rssi;
    }

    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        if (this == arg0) return true;
        if (!(arg0 instanceof IbeaconBean)) return false;
        IbeaconBean p = (IbeaconBean) arg0;
        if (key.equals(p.key) && mac.equals(p.mac)) {
            p.rssi = String.valueOf(Math.min(Math.abs(Integer.parseInt(p.rssi)), Math.abs(Integer.parseInt(rssi))));
        }
        return key.equals(p.key) && mac.equals(p.mac);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        String str = key + mac;
        return str.hashCode();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Student)) return false;
//
//        Student student = (Student) o;
//
//        if (id != student.id) return false;
//        if (age != student.age) return false;
//        return name != null ? name.equals(student.name) : student.name == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + age;
//        return result;
//    }
}
