package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.my.fragment.AddressDialogFragment;

/**
 * Created by  wsl
 * on 2019/4/15 14:34
 */
public class TwentyNineActivity extends AppCompatActivity {
    private static final String TAG = "TwentyNineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_nine_layout);
        findViewById(R.id.btn_taobao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaobao();
            }
        });
        findViewById(R.id.btn_jingdong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJD();
            }
        });
        findViewById(R.id.btn_webjd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //跳转浏览器，打开详情页
                Uri uri = Uri.parse("https://item.jd.com/231023.html");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_toMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirtyActivity.startActivity(TwentyNineActivity.this);
            }
        });
        final Button viewById = findViewById(R.id.btn_click);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressDialogFragment.newInstance(new AddressDialogFragment.OnItemClick() {
                    @Override
                    public void onItemClick(String item) {
                        viewById.setText(item);
                    }
                }).show(getSupportFragmentManager(), "address");
            }
        });
        boolean a = true;
        boolean b = false;
        boolean c = false;
        boolean d = false;
        boolean e = false;
        boolean f = false;
        Log.i(TAG + "c|=b", String.valueOf(c |= b));
        Log.i(TAG + " c|= a", String.valueOf(c |= a));
        Log.i(TAG + " c|= d", String.valueOf(c |= d));
        Log.i(TAG + " c|= e", String.valueOf(c |= e));
        Log.i(TAG + " c|= f", String.valueOf(c |= f));

    }

    /**
     * 跳转淘宝详情页
     */
    public void openTaobao() {
        if (checkPackage("com.taobao.taobao")) { //url:淘宝商品详情
            String url = "https://item.taobao.com/item.htm?id=539789035577&ali_refid=a3_430406_1007:1124066525:N:485184283370953001_0_100:d45485b3013535b0cc4164b7cd5b7523&ali_trackid=1_d45485b3013535b0cc4164b7cd5b7523&spm=a21bo.50862.201874-sales.8.UYm99R";
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
            startActivity(intent);
        }
    }

    /**
     * 跳转京东详情页
     */
    public void openJD() {
        if (checkPackage("com.jingdong.app.mall")) {
            String urlDetail = "https://item.jd.com/231023.html";// 这是京东商品详情页
            String id = "231023";//需要提取商品id，添加到下面url，不能单独将商品详情页作为url传入
            String url = "openapp.jdmobile://virtual?params=%7B%22sourceValue%22:%220_productDetail_97%22,%22des%22:%22productDetail%22,%22skuId%22:%22" + id + "%22,%22category%22:%22jump%22,%22sourceType%22:%22PCUBE_CHANNEL%22%7D";
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            intent.setClassName("com.jingdong.app.mall", "com.jd.lib.productdetail.ProductDetailActivity");
            //不需要
            startActivity(intent);
        }
    }

    /**
     * 检测该包名所对应的应用是否存在 ** @param packageName * @return
     */
    public boolean checkPackage(String packageName) {
        if (packageName == null || "".equals(packageName)) return false;
        try { //手机已安装，返回true
            getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            //手机未安装，跳转到应用商店下载，并返回false
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
            return false;
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyNineActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
