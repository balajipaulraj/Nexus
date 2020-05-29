package com.example.admin.bibleapp.Retrofit;


import com.example.admin.bibleapp.Model.Answers;
import com.example.admin.bibleapp.Model.EnglishLabel;
import com.example.admin.bibleapp.Model.Event;
import com.example.admin.bibleapp.Model.Language;
import com.example.admin.bibleapp.Model.Media;
import com.example.admin.bibleapp.Model.MenuItems;
import com.example.admin.bibleapp.Model.Page;
import com.example.admin.bibleapp.Model.Reg;
import com.example.admin.bibleapp.Model.Sync;
import com.example.admin.bibleapp.Model.UserLogin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    //////////////////////////////////////user///////////////////////////////////////////////////
    @FormUrlEncoded
    @POST("/user/auth")
    Call<UserLogin> login(@Field("emailOrPhone") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("/user/edit-password")
    Call<UserLogin> editpassword(@Field("userId") Integer userid, @Field("email") String email, @Field("oldpassword") String oldpassword
            , @Field("newpassword") String newpassword);

    @FormUrlEncoded
    @POST("/user/register")
    Call<Reg> signup(@Field("email") String email, @Field("password") String password,
                     @Field("firstname") String firstname, @Field("lastname") String lastname,
                     @Field("phone") String phone, @Field("deviceToken") String deviceToken);

    @FormUrlEncoded
    @PUT("/user/edit-profile")
    Call<UserLogin> editprofile(@Field("userId ") String userId,
                                @Field("firstname") String firstname,
                                @Field("lastname") String lastname,
                                @Field("phone") String phone,
                                @Field("gender") String gender,
                                @Field("dob") String dob,
                                @Field("profilePic") String profilePic,
                                @Field("address") String address,
                                @Field("city") String city,
                                @Field("state") String state,
                                @Field("pincode") String pincode,
                                @Field("published ") String published
    );

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////Menu//////////////////////////////////////////////

    @GET("/Menu/sidemenu?")
    Call<ArrayList<MenuItems>> getSideMenu(@Query("userId") int userid);

    @GET("/Menu/footermanu?")
    Call<ArrayList<MenuItems>> getFooterMenu(@Query("userId") int userid);

    @GET("/Menu/sidemenu/lang/{id}")
    Call<List<MenuItems>> getSideLangMenu(@Query("userId") int userid, @Path("id") int id);

    @GET("/Menu/footermenu/lang/{id}")
    Call<List<MenuItems>> getFooterLangMenu(@Query("userId") int userid, @Path("id") int id);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////Answers//////////////////////////////////////////////////////

    @GET("/Menu/answers/{id}")
    Call<List<Answers>> getQuestion(@Query("userId") int userid, @Path("id") int id);

    @FormUrlEncoded
    @POST("/answers")
    Call<List<Answers>> postAnswer(@Field("mediaId") int mediaid, @Field("qnreId") int qnreId
            , @Field("userId") int userId, @Body List<Answers> ans);

    @FormUrlEncoded
    @PUT("/answers/")
    Call<List<Answers>> putAnswer(@Field("mediaId") int mediaid, @Field("qnreId") int qnreId
            , @Field("userId") int userId, @Body List<Answers> ans);


    ///////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////Language///////////////////////////////////////////////////


    @GET("/Label")
    Call<Language> getLanguage(@Query("userId") int userid);

    @GET("/Label/getEnglishLabel")
    Call<EnglishLabel> getEnglishLanguage(@Query("userId") int userid);

    @GET("/Label/getAllLabel/{id}")
    Call<Language> getAllLabel(@Query("userId") int userid, @Path("id") int id);

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////Event////////////////////////////////////////////////////

    @GET("/Event")
    Call<ArrayList<Event>> getEvent(@Query("userId") int userid);

    @GET("/Event/{id}")
    Call<ArrayList<Event>> getIdEvent(@Path("id") int id, @Query("userId") int userid);

    @GET("/Event/lang/{id}")
    Call<Event> getLangEvent(@Query("userId") int userid, @Path("id") int id);

    ////////////////////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////Media////////////////////////////////////////////////////

    @GET("/Media")
    Call<ArrayList<Media>> getMedia(@Query("userId") int userid);

    @GET("/Media/{id}")
    Call<ArrayList<Media>> getIdMedia(@Path("id") int id, @Query("userId") int userid);

    @GET("/Media/lang/{id}")
    Call<Media> getLangMedia(@Query("userId") int userid, @Path("id") int id);

    ////////////////////////////////////////////////////////////////////////////////////////////////


    // ///////////////////////////////Page////////////////////////////////////////////////////

    @GET("/Page")
    Call<ArrayList<Page>> getPage(@Query("userId") int userid);

    @GET("/Page/{id}")
    Call<ArrayList<Page>> getIdPage(@Path("id") int id, @Query("userId") int userid);

    @GET("/Page/lang/{id}")
    Call<Page> getLangPage(@Query("userId") int userid, @Path("id") int id);

    ////////////////////////////////////////////////////////////////////////////////////////////////


    @GET("/Updates")
    Call<ArrayList<Sync>> getSync(@Query("userId") int userid);
}
