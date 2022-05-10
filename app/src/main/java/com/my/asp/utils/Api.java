package com.my.asp.utils;

import com.my.asp.model.ContactModels;
import com.my.asp.model.CountryListModels;
import com.my.asp.model.NewsModels;
import com.my.asp.model.NotificationList;
import com.my.asp.model.ProductDetailsModels;
import com.my.asp.model.ProductModels;
import com.my.asp.model.TrackOrderModels;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    String Api_add_registor = "add_registor";
    String Api_get_products = "get_products";
    String Api_get_product_detail = "get_product_detail";
    String Api_get_news = "get_news";
    String Api_get_my_order = "get_my_order";
    String Api_contact = "contact";
    String Api_country_list = "country_list";
    String Api_get_notification = "get_notification";

    @POST(Api_get_products)
    Call<ProductModels> get_products();

    @POST(Api_get_news)
    Call<NewsModels> get_news();

   @POST(Api_country_list)
    Call<CountryListModels> Api_country_list();

    @FormUrlEncoded
    @POST(Api_get_product_detail)
    Call<ProductDetailsModels> Api_get_products_details(
            @Field("product_id") String product_id
    );

    @FormUrlEncoded
    @POST(Api_add_registor)
    Call<ResponseBody> Api_add_registor(
            @Field("register_id") String register_id
    );

    @FormUrlEncoded
    @POST(Api_get_my_order)
    Call<TrackOrderModels> Api_get_my_order(
            @Field("order_number") String order_number
    );

    @FormUrlEncoded
    @POST(Api_contact)
    Call<ContactModels> Api_contact(
            @Field("name") String name,
            @Field("country_id") String country_id,
            @Field("lat") String lat,
            @Field("lon") String lon,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("dapartment_of_interests") String dapartment_of_interests,
            @Field("subject_matter") String subject_matter,
            @Field("message") String message,
            @Field("address") String address
    );

    @POST(Api_get_notification)
    Call<NotificationList> Api_get_notification();

}
