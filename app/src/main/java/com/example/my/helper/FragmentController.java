package com.example.my.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * 创建日期：2017/12/4
 * 描述:fragment控制器
 * 作者:张晓阳
 */

public class FragmentController {
    int containerId;
    ArrayList<Fragment> fragments;
    private FragmentManager fm;
    private static FragmentController controller;

    private FragmentController(int containerId, FragmentManager fm, ArrayList<Fragment> fragments) {
        this.containerId = containerId;
        this.fm = fm;
        this.fragments = fragments;
        initFragment();
    }

    public static FragmentController getInstance(int containerId, FragmentManager fm, ArrayList<Fragment> fragments) {
        if (controller == null) {
            controller = new FragmentController(containerId, fm, fragments);
        }
        return controller;
    }

    private void initFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            ft.add(containerId, fragment);
        }
        ft.commit();
    }

    public void showFragment(int position) {
        hideFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragments.get(position));
        ft.commit();
    }

    public void hideFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

    public static void close() {
        if (controller != null) {
            controller = null;
            System.gc();
        }
    }
}