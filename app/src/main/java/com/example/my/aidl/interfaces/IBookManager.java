package com.example.my.aidl.interfaces;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import com.example.my.bean.Book;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6.
 */

public interface IBookManager extends IInterface {
    static final String DESCRIPTOR = "com.example.my.aidl.interfaces.IBookManager";
    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;
}
