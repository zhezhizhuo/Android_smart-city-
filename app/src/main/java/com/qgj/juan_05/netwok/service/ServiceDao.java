package com.qgj.juan_05.netwok.service;

import android.widget.Toolbar;

import com.qgj.juan_05.adpater.HomeNewsInfoPlAdapter;
import com.qgj.juan_05.netwok.model.*;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceDao {
    //登录的接口
    @POST("/prod-api/api/login")
    @Headers("Content-Type:application/json")
    Call<LoginModel> login(@Body AccountModel accountModel);
    //获取用户信息的接口
    @GET("/prod-api/api/common/user/getInfo")
    Call<UserInfoModel> getUserInfo(@Header("Authorization") String Authorization);
    //修改密码的接口
    @PUT("/prod-api/api/common/user/resetPwd")
    Call<DataModel> upDatePassWold(@Header("Authorization") String Authorization, @Body UpPassWordModel model);
    //修改用户信息的接口
    @PUT("/prod-api/api/common/user")
    Call<DataModel> upUserinfo(@Header("Authorization") String Authorization,@Body UpUserinfoModel model);
    //轮播图的图片信息
    @GET("/prod-api/api/rotation/list")
    Call<AbnnerModel> getBannerType(@Query("type") String type);
    // 获取广告
    @GET("prod-api/api/park/rotation/list")
    Call<AbnnerModel> getBannerImg();
    //外卖的广告
    @GET("/prod-api/api/takeout/rotation/list")
    Call<AbnnerModel> getTackOut();
    // 店家相关接
    @GET("/prod-api/api/takeout/seller/list")
    Call<TackOutModel> gethotel();
    //意见反馈的信息
    @POST("/prod-api/api/common/feedback")
    Call<DataModel> sendFeedBack(@Header("Authorization") String Authorization,@Body FeedBackModel model);
    //获取所有服务的接口
    @GET("/prod-api/api/service/list")
    Call<HomeServiceModel>getAllService();
    //获取home页面的新闻列表
    @GET("/prod-api/press/category/list")
    Call<HomeNewsModel>getAllHomeNewsType();

    //获取home页面的新闻列表的id获取详细新闻
    @GET("/prod-api/press/press/list")
    Call<HomeNewAllModel>getHomeNewsById(@Query("type") int id);
    //根据id获取新闻详情信息
    @GET("/prod-api/press/press/{id}")
    Call<NewsInfoModel>getNewsInfoById(@Path("id") int id);
    //根据id获取新闻评论
    @GET("/prod-api/press/comments/list")
    Call<NewsInfoplModel>getNewsPingLunById(@Query("newsId") int id);

    //查询所有的停车场
    @GET("/prod-api/api/park/lot/list")
    Call<PackModel>getPackAll();
    //根据id查询详细停车信息
    @GET("/prod-api/api/park/lot/{id}")
    Call<PackInfoModel>getPackInfoById(@Path("id") int id);
    //获取所有的记录
    @GET("/prod-api/api/park/lot/record/list")
    Call<PackJLModel>getPackJiLuAll();
    //根据出场时间获取记录
    @GET("/prod-api/api/park/lot/record/list")
    Call<PackJLModel>getPackJiLuEntryTime(@Query("entryTime") String entrytime);
    //根据入场时间获取记录
    @GET("/prod-api/api/park/lot/record/list")
    Call<PackJLModel>getPackJiLuOutTime(@Query("outTime") String outTime);
    //出场时间获取记录入场时间获取记录
    @GET("/prod-api/api/park/lot/record/list")
    Call<PackJLModel>getPackJiLu(@Query("entryTime") String entrytime,@Query("outTime") String outTime);
    //根据页面插曲
    @GET("/prod-api/api/park/lot/record/list")
    Call<PackJLModel>getPackJiLuPager(@Query("pageNum") int pageNum,@Query("pageSize") int pageSize);
    //查询天气的接口
    @GET("/prod-api/api/common/weather")
    Call<WeartherModel>getWeatherInfo();
    @GET("/prod-api/api/common/weather/today")
    Call<WearthInfoModel>getWeatherInfoToday();
    //获取所有的医院
    @GET("/prod-api/api/hospital/hospital/list")
    Call<OutPationModel>getOutpatientAll();

    //根据id获取医院的banner
    @GET("/prod-api/api/hospital/banner/list")
    Call<OutAbnnerModel>getHospitalBannerById(@Query("id") int id);
    //获取所有公交路线
    @GET("/prod-api/api/bus/line/list")
    Call<BusLienModel>getBusLienAll();
    //获据bus的id获取所有的路线
    @GET("/prod-api/api/bus/stop/list")
    Call<BusLineInfoModel>getBusLienInfoById(@Query("linesId") int id);
    //获取活动管理的轮播图
    @GET("/prod-api/api/activity/rotation/list")
    Call<ActivityBannerModel>getActivityBanner();
    ////获取活动管理的分类
    @GET("/prod-api/api/activity/category/list")
    Call<ActivityTypeModel>getActivityType();
    ////根据活动管理分类的id 查询所有列表信息
    @GET("/prod-api/api/activity/activity/list")
    Call<ActivyInfoModel>getActivityTypeInfoById(@Query("categoryId") int id);
    ////根据类查询所有列表信息的id 获取详细信息
    @GET("/prod-api/api/activity/activity/{id}")
    Call<ActivityInfoModel>getActivityInfoById(@Path("id") int id);
    //查看用户是否报名
    @GET("/prod-api/api/activity/signup/check")
    Call<BaoMingModel>getActivityStatus(@Header("Authorization") String Authorization,@Query("activityId") int id);
    //报名改活动
    @POST("/prod-api/api/activity/signup")
    Call<DataModel>baomingActivity(@Header("Authorization") String Authorization,@Body BaoMing bm);
    //查询就诊卡
    @GET("/prod-api/api/hospital/patient/list")
    Call<CardInfoModel> getCardInfo(@Header("Authorization") String Authorization);
    //查询专家
    @GET("/prod-api/api/hospital/category/list")
    Call<CardInfoModel> getDoctorType(@Query("type") int id);
    //添加就诊卡
    @POST("/prod-api/api/hospital/patient")
    Call<DataModel> addCard(@Header("Authorization") String Authorization,@Body AddCardModel addCardModel);
    //查询所有科室
    @GET("/prod-api/api/hospital/category/list")
    Call<CardDepartmentModel> getDepartmentAll();
    @GET("/prod-api/api/hospital/category/list")
    Call<CardDepartmentModel> getDepartmentAll(@Query("type") int type);
    //查询影片信息列
    @GET("/prod-api/api/movie/film/list")
    Call<MovieModel> getMovieAll();
    //
    @GET("/prod-api/api/movie/film/preview/list")
    Call<HotMovieModel> getMovieHotAll();
    //根据id查看所有的xinx
    @GET("/prod-api/api/movie/film/detail/{id}")
    Call<MoviesInfoModel> getMovieInfoById(@Path("id")int id);
    //根据id查看所有的评论
    @GET("/prod-api/api/movie/film/comment/list")
    Call<MoviePlModel> getMoviePlById(@Query("movieId") int id);
    //生活服务的图
    @GET("/prod-api/api/living/category/list")
    Call<LifeModel> getLifeAll();
    //生活缴费轮播图
    @GET("/prod-api/api/living/rotation/list")
    Call<AbnnerModel> getLifeBanner();
    //电影广告
    @GET("/prod-api/api/movie/rotation/list")
    Call<AbnnerModel> getMovieBanner();
    //房源
    @GET("/prod-api/api/house/housing/list")
    Call<HouseModel> getHouseAll();
    //外卖分类
    @GET("/prod-api/api/takeout/theme/list")
    Call<WaiMaiModel> getWaiMaiFenLei();
    //外卖列表
    //根据分类Id查询外卖列表
    @GET("/prod-api/api/takeout/seller/list")
    Call<WaiMai2Model> getWaiMaiInfoAll(@Query("themeId") int id);
    //影院
    @GET("/prod-api/api/movie/theatre/list")
    Call<CinameModel> getCinema(@Query("pageNum") int pageNum,@Query("pageSize") int pageSize);
    //电影评论
    @POST("/prod-api/api/movie/film/comment")
    @Headers( value = "Content-Type:application/json")
    Call<DataModel> sendMoviePl(@Body SendMoviePlModel moviePlModel,@Header("Authorization") String token);
}
