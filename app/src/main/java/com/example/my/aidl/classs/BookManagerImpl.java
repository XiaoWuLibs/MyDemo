package com.example.my.aidl.classs;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.my.aidl.interfaces.IBookManager;
import com.example.my.bean.Book;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6.
 */

public class BookManagerImpl extends Binder implements IBookManager {
    public BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

//    public static IBookManager asInterface(IBinder obj) {
//        if (obj == null) {
//            return null;
//        }
//        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
//        if ((iin != null) && (iin instanceof IBookManager)) {
//            return ((IBookManager) iin);
//        }
//        return new BookManagerImpl().Proxy(obj);
//    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
//                data.enforceInterface(DESCRIPTOR);
//                List<Book> result = this.getBookList();
//                reply.writeNoException();
//                reply.writeTypedList(result);
                return true;
            }
            case TRANSACTION_addBook: {
//                data.enforceInterface(DESCRIPTOR);
//                Book arg0;
//                if (0 != data.readInt()) {
//                    arg0 = Book.CREATOR.c
//                } else {
//                }
            }
        }
        return super.onTransact(code, data, reply, flags);
    }
}
