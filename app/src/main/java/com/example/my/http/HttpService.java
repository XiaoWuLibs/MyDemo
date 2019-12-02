package com.example.my.http;

import com.example.my.bean.GetModuleBean;
import com.example.my.bean.LoginBeanForUrl;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by  wsl
 * on 2019/11/27 14:09
 * http api接口
 */
public interface HttpService {
    public static final String BASE_URL_FOR_SERVICE = "http://qzfu.zd.119xiehui.com/";


    public static final String GET_MODULE_DATA = "http://lfjic.test.119xiehui.com/";

    @FormUrlEncoded
    @POST("api/BasicConfig/GetAllListLocation")
    Observable<LoginBeanForUrl> loginForService(@Field("LoginName") String userName, @Field("LoginPwd") String password);

    @GET("APIFiles/phone/sign.json")
    Observable<GetModuleBean> getModule();
}
