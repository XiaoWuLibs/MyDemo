package com.example.my.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/1/25.
 * 数据库表-书库
 */

public class Books extends DataSupport implements Parcelable {
    private int id;
    private String bookName;
    private String bookAuther;
    private String bookPrice;
    private String bookDecreption;
    private String bookType;

    public Books() {
    }

    protected Books(Parcel in) {
        id = in.readInt();
        bookName = in.readString();
        bookAuther = in.readString();
        bookPrice = in.readString();
        bookDecreption = in.readString();
        bookType = in.readString();
    }

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuther() {
        return bookAuther;
    }

    public void setBookAuther(String bookAuther) {
        this.bookAuther = bookAuther;
    }

    public String getBookDecreption() {
        return bookDecreption;
    }

    public void setBookDecreption(String bookDecreption) {
        this.bookDecreption = bookDecreption;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(bookName);
        dest.writeString(bookAuther);
        dest.writeString(bookPrice);
        dest.writeString(bookDecreption);
        dest.writeString(bookType);
    }
}
