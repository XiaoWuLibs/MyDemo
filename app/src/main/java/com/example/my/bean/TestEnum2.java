package com.example.my.bean;

/**
 * Created by  wsl
 * on 2019/7/28 22:33
 * 测试枚举
 */
public enum TestEnum2 {
    /**
     * 人员1
     */
    XIAO_MING(1, "詹姆斯明", 12, "男"),
    /**
     * 人员2
     */
    XIAO_WANG(2, "詹姆斯王", 20, "男"),
    /**
     * 人员3
     */
    XIAO_ZHANG(3, "詹姆斯张", 30, "男"),
    /**
     * 人员4
     */
    XIAO_ZHAO(4, "詹姆斯赵", 21, "男");

    public int primaryKey;
    public String name;
    public int age;
    public String sex;

    TestEnum2(int primaryKey, String name, int age, String sex) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "id:" + primaryKey + ";name:" + name + ";age:" + age + ";sex:" + sex;
    }}
