package com.example.my.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/1/25.
 * 数据库表-购物车
 */

public class ShopCar extends DataSupport implements Parcelable {
    private int id;
    private String bookName;
    private String bookAuthor;
    private String bookPrice;
    private String bookDetail;
    private String bookType;
    private String bookNum;

    public ShopCar() {
    }

    protected ShopCar(Parcel in) {
        id = in.readInt();
        bookName = in.readString();
        bookAuthor = in.readString();
        bookPrice = in.readString();
        bookDetail = in.readString();
        bookType = in.readString();
        bookNum = in.readString();
    }

    public static final Creator<ShopCar> CREATOR = new Creator<ShopCar>() {
        @Override
        public ShopCar createFromParcel(Parcel in) {
            return new ShopCar(in);
        }

        @Override
        public ShopCar[] newArray(int size) {
            return new ShopCar[size];
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

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(String bookDetail) {
        this.bookDetail = bookDetail;
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

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(bookName);
        dest.writeString(bookAuthor);
        dest.writeString(bookPrice);
        dest.writeString(bookDetail);
        dest.writeString(bookType);
        dest.writeString(bookNum);
    }
}
