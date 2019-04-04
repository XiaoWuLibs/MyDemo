package com.example.my.grid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.adapter.BooksListAdapter;
import com.example.my.db.Books;
import com.example.my.db.ShopCar;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 * 分类
 */

public class Fragment2 extends Fragment {
    private Button btn_wenxue;
    private Button btn_yanqing;
    private Button btn_dongman;
    private Button btn_xuexi;
    private ListView lv_books;
    private TextView tv_showResult;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, null);
        btn_wenxue = view.findViewById(R.id.btn_wenxue);
        btn_yanqing = view.findViewById(R.id.btn_yanqing);
        btn_dongman = view.findViewById(R.id.btn_dongman);
        lv_books = view.findViewById(R.id.lv_books);
        tv_showResult = view.findViewById(R.id.tv_showResult);

        btn_xuexi = view.findViewById(R.id.btn_xuexi);
        btn_wenxue.setOnClickListener(onClickListener);
        btn_yanqing.setOnClickListener(onClickListener);
        btn_dongman.setOnClickListener(onClickListener);
        btn_xuexi.setOnClickListener(onClickListener);
        btn_wenxue.setBackgroundColor(getResources().getColor(R.color.white_gray));
        btn_yanqing.setBackgroundColor(getResources().getColor(R.color.white));
        btn_dongman.setBackgroundColor(getResources().getColor(R.color.white));
        btn_xuexi.setBackgroundColor(getResources().getColor(R.color.white));
        List<Books> wxbooks = queryData("文学类");
        if (wxbooks != null && !wxbooks.isEmpty() && wxbooks.size() != 0) {
            tv_showResult.setVisibility(View.GONE);
            lv_books.setVisibility(View.VISIBLE);
            BooksListAdapter wxAdapter = new BooksListAdapter(getActivity(), wxbooks, onClickListener);
            lv_books.setAdapter(wxAdapter);
            lv_books.setOnItemClickListener(onItemClickListener);
        } else {
            tv_showResult.setVisibility(View.VISIBLE);
            lv_books.setVisibility(View.GONE);
        }
        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_wenxue:
                    btn_wenxue.setBackgroundColor(getResources().getColor(R.color.white_gray));
                    btn_yanqing.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_dongman.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_xuexi.setBackgroundColor(getResources().getColor(R.color.white));
                    List<Books> wxbooks = queryData("文学类");
                    if (wxbooks != null && !wxbooks.isEmpty() && wxbooks.size() != 0) {
                        tv_showResult.setVisibility(View.GONE);
                        lv_books.setVisibility(View.VISIBLE);
                        BooksListAdapter wxAdapter = new BooksListAdapter(getActivity(), wxbooks, onClickListener);
                        lv_books.setAdapter(wxAdapter);
                        lv_books.setOnItemClickListener(onItemClickListener);
                    } else {
                        tv_showResult.setVisibility(View.VISIBLE);
                        lv_books.setVisibility(View.GONE);
                    }
                    break;
                case R.id.btn_yanqing:
                    btn_wenxue.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_yanqing.setBackgroundColor(getResources().getColor(R.color.white_gray));
                    btn_dongman.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_xuexi.setBackgroundColor(getResources().getColor(R.color.white));
                    List<Books> yqbooks = queryData("言情类");
                    if (yqbooks != null && !yqbooks.isEmpty() && yqbooks.size() != 0) {
                        tv_showResult.setVisibility(View.GONE);
                        lv_books.setVisibility(View.VISIBLE);
                        BooksListAdapter yqAdapter = new BooksListAdapter(getActivity(), yqbooks, onClickListener);
                        lv_books.setAdapter(yqAdapter);
                        lv_books.setOnItemClickListener(onItemClickListener);
                    } else {
                        tv_showResult.setVisibility(View.VISIBLE);
                        lv_books.setVisibility(View.GONE);
                    }
                    break;
                case R.id.btn_dongman:
                    btn_wenxue.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_yanqing.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_dongman.setBackgroundColor(getResources().getColor(R.color.white_gray));
                    btn_xuexi.setBackgroundColor(getResources().getColor(R.color.white));
                    List<Books> dmbooks = queryData("动漫类");
                    if (dmbooks != null && !dmbooks.isEmpty() && dmbooks.size() != 0) {
                        tv_showResult.setVisibility(View.GONE);
                        lv_books.setVisibility(View.VISIBLE);
                        BooksListAdapter dmAdapter = new BooksListAdapter(getActivity(), dmbooks, onClickListener);
                        lv_books.setAdapter(dmAdapter);
                        lv_books.setOnItemClickListener(onItemClickListener);
                    } else {
                        tv_showResult.setVisibility(View.VISIBLE);
                        lv_books.setVisibility(View.GONE);
                    }
                    break;
                case R.id.btn_xuexi:
                    btn_wenxue.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_yanqing.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_dongman.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_xuexi.setBackgroundColor(getResources().getColor(R.color.white_gray));
                    List<Books> xxbooks = queryData("学习类");
                    if (xxbooks != null && !xxbooks.isEmpty() && xxbooks.size() != 0) {
                        tv_showResult.setVisibility(View.GONE);
                        lv_books.setVisibility(View.VISIBLE);
                        BooksListAdapter xxAdapter = new BooksListAdapter(getActivity(), xxbooks, onClickListener);
                        lv_books.setAdapter(xxAdapter);
                        lv_books.setOnItemClickListener(onItemClickListener);
                    } else {
                        tv_showResult.setVisibility(View.VISIBLE);
                        lv_books.setVisibility(View.GONE);
                    }
                    break;
                case R.id.iv_shopCar:
                    Books books = (Books) view.getTag();
                    if (addToShopCar(books)) {
                        Toast.makeText(getActivity(), "加入购物车", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "加入购物车失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    /**
     * listView中Item点击事件
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Books books = (Books) parent.getItemAtPosition(position);
            BookDetailActivity.startActivity(getActivity(), books);
        }
    };

    private List<Books> queryData(String bookType) {
        return DataSupport.where("bookType = ?", bookType).find(Books.class);
    }

    private boolean addToShopCar(Books books) {
        try {
            ShopCar shopCar = new ShopCar();
            shopCar.setBookName(books.getBookName());
            shopCar.setBookAuthor(books.getBookAuther());
            shopCar.setBookType(books.getBookType());
            shopCar.setBookPrice(books.getBookPrice());
            shopCar.setBookDetail(books.getBookDecreption());
            shopCar.setBookNum("1");
            shopCar.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
